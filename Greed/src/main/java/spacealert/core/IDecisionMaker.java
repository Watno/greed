package spacealert.core;

import spacealert.core.gamestates.GameStateWithPrivateInfo;
import spacealert.core.planningPhase.IPlanningPhaseExposedToDecisionMaker;
import spacealert.core.planningPhase.eventSequences.events.Notification;

public interface IDecisionMaker {
    Runnable allowMakingDecisionsForPlanningPhase(IPlanningPhaseExposedToDecisionMaker planningPhase);

    void sendGameState(GameStateWithPrivateInfo gameState);

    void sendNotification(Notification notification);

    void sendResult(GameResult result);
}
