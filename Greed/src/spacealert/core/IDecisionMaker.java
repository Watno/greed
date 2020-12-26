package spacealert.core;

import spacealert.core.planningPhase.IPlanningPhaseExposedToDecisionMaker;
import spacealert.core.planningPhase.IPlayerExposedToDecisionMaker;

public interface IDecisionMaker {
    Runnable allowMakingDecisionsForPlanningPhase(IPlanningPhaseExposedToDecisionMaker planningPhase, IPlayerExposedToDecisionMaker player);
}
