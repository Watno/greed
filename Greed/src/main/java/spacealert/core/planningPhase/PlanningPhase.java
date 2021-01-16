package spacealert.core.planningPhase;

import spacealert.core.Color;
import spacealert.core.IDecisionMaker;
import spacealert.core.actionCards.ActionCard;
import spacealert.core.gamestates.GameStateWithPrivateInfo;
import spacealert.core.gamestates.PublicGameState;
import spacealert.core.planningPhase.commands.actionCards.IPlanningPhaseCommand;

import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PlanningPhase implements IPlanningPhaseExposedToDecisionMaker, Runnable {
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
        for (var player : players.values()) {
            player.drawCards(drawCards(5));
        }
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
        var timer = new ScheduledThreadPoolExecutor(1);
        var finishTask = timer.schedule(() -> {
            for (var thread : decisionMakerThreads) {
                thread.interrupt();
            }
        }, 100, TimeUnit.SECONDS);
        try {
            finishTask.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void flipCardOnAndroidActionBoard(UUID cardId) {
        if (players.size() > 1) return; //Flipping on android boards is only allowed in solo mode;
        for (var android : androids) {
            android.flipCardOnActionBoard(cardId);
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

    private Optional<Android> getAndroidByColor(Color color) {
        return androids.stream().filter(x -> x.hasColor(color)).findFirst();
    }

    private void broadcastGameState() {
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

    private Collection<ActionCard> drawCards(int number) {
        return IntStream.range(0, number).mapToObj(x -> drawCard()).collect(Collectors.toList());
    }

    private ActionCard drawCard() {
        return deck.pop();
    }

}
