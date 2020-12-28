package spacealert.core.planningPhase;

import spacealert.core.IDecisionMaker;
import spacealert.core.actionCards.ActionBoard;
import spacealert.core.planningPhase.commands.actionCards.IPlanningPhaseCommand;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlanningPhase implements IPlanningPhaseExposedToDecisionMaker {
    private final Map<IDecisionMaker, Player> players;
    private final List<ActionBoard> androidActionBoards;

    public PlanningPhase(Collection<IDecisionMaker> decisionMakers, int numberOfPlayers) {
        players = decisionMakers.stream().collect(Collectors.toMap(x -> x, x -> new Player()));
        androidActionBoards = Stream.generate(ActionBoard::new)
                .limit(numberOfPlayers - decisionMakers.size())
                .collect(Collectors.toList());
    }

    @Override
    public void execute(IDecisionMaker decisionMaker, IPlanningPhaseCommand command) {
        command.execute(this, players.get(decisionMaker));
    }

    public void execute() {
        var decisionMakerThreads = players.keySet().stream()
                .map(x -> new Thread(x.allowMakingDecisionsForPlanningPhase(this)))
                .collect(Collectors.toList());
        for (var thread : decisionMakerThreads) {
            thread.start();
        }
        var timer = new Timer();
        var lock = new Object();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (var thread : decisionMakerThreads) {
                    thread.interrupt();
                }
                synchronized (lock) {
                    lock.notifyAll();
                }
            }
        }, 10000);
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void flipCardOnAndroidActionBoard(UUID cardId) {
        if (players.size() > 1) return; //Flipping on android boards is only allowed in solo mode;
        for (var actionBoard : androidActionBoards) {
            actionBoard.flipCardById(cardId);
        }
    }

    public void placeCardOnAndroidActionBoard(Player player, UUID actionBoardId, UUID cardId, int position) {
        getAndroidActionBoardById(actionBoardId)
                .ifPresent(x -> player.placeCardOnActionBoard(x, cardId, position));
    }

    public void retrieveCardFromAndroidActionBoard(Player player, UUID actionBoardId, UUID cardId) {
        if (players.size() > 1) return; //Returning from android boards is only allowed in solo mode;
        getAndroidActionBoardById(actionBoardId)
                .ifPresent(x -> player.retrieveCardFromActionBoard(x, cardId));
    }

    private Optional<ActionBoard> getAndroidActionBoardById(UUID actionBoardId) {
        return androidActionBoards.stream()
                .filter(x -> x.id.equals(actionBoardId))
                .findFirst();
    }


}
