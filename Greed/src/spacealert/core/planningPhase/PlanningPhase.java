package spacealert.core.planningPhase;

import spacealert.core.IDecisionMaker;
import spacealert.core.actionCards.ActionBoard;

import java.util.*;
import java.util.stream.Collectors;

public class PlanningPhase implements IPlanningPhaseExposedToDecisionMaker {
    private Map<IDecisionMaker, Player> players;
    private Collection<ActionBoard> androidActionBoards;

    public void execute() {
        var decisionMakerThreads = players.keySet().stream()
                .map(x -> x.allowMakingDecisionsForPlanningPhase(this, players.get(x)))
                .collect(Collectors.toList());
        for (var thread : decisionMakerThreads) {
            thread.run();
        }
        var timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (var thread : decisionMakerThreads) {
                    thread.;
                }
            }
        });
    }

    @Override
    public void flipCardOnAndroidActionBoard(UUID cardId) {
        if (players.size() > 1) return; //Flipping on android boards is only allowed in solo mode;
        for (var actionBoard : androidActionBoards) {
            actionBoard.flipCardById(cardId);
        }
    }

    @Override
    public void placeCardOnAndroidActionBoard(IPlayerExposedToDecisionMaker player, UUID actionBoardId, UUID cardId, int position) {
        getAndroidActionBoardById(actionBoardId)
                .ifPresent(x -> ((Player) player).placeCardOnActionBoard(x, cardId, position));
    }

    @Override
    public void retrieveCardFromAndroidActionBoard(IPlayerExposedToDecisionMaker player, UUID actionBoardId, UUID cardId) {
        if (players.size() > 1) return; //Returning from android boards is only allowed in solo mode;
        getAndroidActionBoardById(actionBoardId)
                .ifPresent(x -> ((Player) player).retrieveCardFromActionBoard(x, cardId));
    }

    private Optional<ActionBoard> getAndroidActionBoardById(UUID actionBoardId) {
        return androidActionBoards.stream()
                .filter(x -> x.id.equals(actionBoardId))
                .findFirst();
    }

}
