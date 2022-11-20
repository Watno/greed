package spacealert.core.planningPhase;

import spacealert.core.Color;
import spacealert.core.IDecisionMaker;
import spacealert.core.Phase;
import spacealert.core.actionCards.ActionCard;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.gamestates.GameStateWithPrivateInfo;
import spacealert.core.gamestates.PublicGameState;
import spacealert.core.planningPhase.commands.actionCards.IPlanningPhaseCommand;
import spacealert.core.planningPhase.eventSequences.EventExecutor;
import spacealert.core.planningPhase.eventSequences.EventSequence;
import spacealert.core.planningPhase.eventSequences.events.Notification;
import spacealert.core.threats.Trajectory;
import spacealert.core.threats.templates.Threat;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PlanningPhase implements IPlanningPhaseExposedToDecisionMaker, IPlanningPhaseExposedToEvents {
    private final Map<IDecisionMaker, Player> players;
    private final List<Android> androids;
    private final Stack<ActionCard> deck;
    private final List<List<Threat>> threatsBySpawn = Stream.<List<Threat>>generate(ArrayList::new).limit(8).collect(Collectors.toList());
    private final Map<Zone, Trajectory> trajectories;
    private final Trajectory internalTrajectory;


    public PlanningPhase(Map<IDecisionMaker, Player> players, List<Android> androids, Map<Zone, Trajectory> trajectories, Trajectory internalTrajectory) {
        this.players = players;
        this.androids = androids;
        this.trajectories = trajectories;
        this.internalTrajectory = internalTrajectory;
        deck = ActionCard.defaultDeck();
    }

    @Override
    public void execute(IDecisionMaker decisionMaker, IPlanningPhaseCommand command) {
        command.execute(this, players.get(decisionMaker));
        broadcastGameState();
    }

    public void run(EventSequence eventSequence) {
        broadcastGameState();
        var decisionMakerThreads = players.keySet().stream()
                .map(x -> new Thread(x.allowMakingDecisionsForPlanningPhase(this), "DecisionMaker"))
                .collect(Collectors.toList());
        for (var thread : decisionMakerThreads) {
            thread.start();
        }
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
                        androids.stream()
                                .map(Android::toPublicAndroidGameState)
                                .collect(Collectors.toList()),
                        threatsBySpawn,
                        trajectories,
                        internalTrajectory);
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
            endPhaseFor(player, phase);
        }
    }

    @Override
    public void addThreat(int turn, Threat threat) {
        threatsBySpawn.get(turn - 1).add(threat);
    }

    @Override
    public void endPhaseFor(Player player, Phase phase) {
        if (player.endPhase(phase)) {
            dealCardsTo(player, 5);
        }
    }

}
