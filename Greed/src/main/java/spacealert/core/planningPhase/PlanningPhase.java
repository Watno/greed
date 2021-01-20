package spacealert.core.planningPhase;

import spacealert.core.Color;
import spacealert.core.IDecisionMaker;
import spacealert.core.Phase;
import spacealert.core.actionCards.ActionCard;
import spacealert.core.gamestates.GameStateWithPrivateInfo;
import spacealert.core.gamestates.PublicGameState;
import spacealert.core.planningPhase.commands.actionCards.IPlanningPhaseCommand;
import spacealert.core.planningPhase.eventSequences.EventExecutor;
import spacealert.core.planningPhase.eventSequences.EventSequence;
import spacealert.core.planningPhase.eventSequences.events.Notification;
import spacealert.core.planningPhase.eventSequences.premades.Mission1;
import spacealert.core.planningPhase.eventSequences.threatProviders.RandomThreatProvider;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PlanningPhase implements IPlanningPhaseExposedToDecisionMaker, IPlanningPhaseExposedToEvents, Runnable {
    private final Map<IDecisionMaker, Player> players;
    private final List<Android> androids;
    private final Stack<ActionCard> deck;

    public PlanningPhase(Collection<IDecisionMaker> decisionMakers, int numberOfPlayers) {
        var availableColors = new ArrayDeque<>(Arrays.asList(Color.values()));
        players = decisionMakers.stream().collect(Collectors.toMap(x -> x, x -> new Player(availableColors.remove())));
        androids = Stream.generate(() -> new Android(availableColors.remove()))
                .limit(numberOfPlayers - decisionMakers.size())
                .collect(Collectors.toList());
        deck = ActionCard.defaultDeck();

    }

    @Override
    public void execute(IDecisionMaker decisionMaker, IPlanningPhaseCommand command) {
        command.execute(this, players.get(decisionMaker));
        broadcastGameState();
    }

    @Override
    public void run() {
        broadcastGameState();
        var decisionMakerThreads = players.keySet().stream()
                .map(x -> new Thread(x.allowMakingDecisionsForPlanningPhase(this), "DecisionMaker"))
                .collect(Collectors.toList());
        for (var thread : decisionMakerThreads) {
            thread.start();
        }
        EventSequence eventSequence = new Mission1(new RandomThreatProvider());
        try {
            new EventExecutor().run(eventSequence, this).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        for (var thread : decisionMakerThreads) {
            thread.interrupt();
        }

    }

    public void flipCardOnAndroidActionBoard(Player player, UUID cardId) {
        if (players.size() > 1) return; //Flipping on android boards is only allowed in solo mode;
        for (var android : androids) {
            player.flipCardOnAndroidActionBoard(android, cardId);
        }
    }

    public void placeCardOnAndroidActionBoard(Player player, Color color, UUID cardId, int position) {
        getAndroidByColor(color)
                .ifPresent(x -> player.placeCardOnAndroidActionBoard(x, cardId, position));
    }

    public void retrieveCardFromAndroidActionBoard(Player player, Color color, UUID cardId) {
        if (players.size() > 1) return; //Returning from android boards is only allowed in solo mode;
        getAndroidByColor(color)
                .ifPresent(x -> player.retrieveCardFromAndroidActionBoard(x, cardId));
    }

    public Optional<Player> getPlayerByColor(Color color) {
        return players.values().stream().filter(x -> x.hasColor(color)).findFirst();
    }

    private Optional<Android> getAndroidByColor(Color color) {
        return androids.stream().filter(x -> x.hasColor(color)).findFirst();
    }

    @Override
    public void broadcastGameState() {
        var publicGameState =
                new PublicGameState(
                        players.values().stream()
                                .map(Player::toPublicPlayerGameState)
                                .collect(Collectors.toList()),
                        androids);
        for (var decisionMaker : players.keySet()) {
            decisionMaker.sendGameState(
                    new GameStateWithPrivateInfo(publicGameState, players.get(decisionMaker).toPrivateGameState())
            );
        }
    }

    private Collection<ActionCard> takeCardsFromDeck(int number) {
        return IntStream.range(0, number).mapToObj(x -> drawCard()).collect(Collectors.toList());
    }

    private ActionCard drawCard() {
        return deck.pop();
    }

    @Override
    public void notifyPlayers(Notification notification) {
        for (var decisionMaker : players.keySet()) {
            decisionMaker.sendNotification(notification);
        }
    }

    @Override
    public void dealCards(int number) {
        for (var player : players.values()) {
            dealCardsTo(player, number);
        }
    }

    private void dealCardsTo(Player player, int number) {
        player.receiveCards(takeCardsFromDeck(number));
    }

    @Override
    public void allowCardPassing() {
        for (var player : players.values()) {
            player.allowToPassACard();
        }
    }

    @Override
    public void disallowCardPassing() {
        for (var player : players.values()) {
            player.disallowToPassACard();
        }
    }

    @Override
    public void endPhase(Phase phase) {
        for (var player : players.values()) {
            player.endPhase(phase);
        }
    }

    @Override
    public void endPhaseFor(Player player, Phase phase) {
        if (player.endPhase(phase)) {
            dealCardsTo(player, 5);
        }
    }

}
