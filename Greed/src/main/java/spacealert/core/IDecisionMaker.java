package spacealert.core;

import spacealert.core.gamestates.GameStateWithPrivateInfo;
import spacealert.core.planningPhase.IPlanningPhaseExposedToDecisionMaker;

public interface IDecisionMaker {
    Runnable allowMakingDecisionsForPlanningPhase(IPlanningPhaseExposedToDecisionMaker planningPhase);

    void sendGameState(GameStateWithPrivateInfo gameState);
}
