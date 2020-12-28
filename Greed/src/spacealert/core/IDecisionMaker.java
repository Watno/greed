package spacealert.core;

import spacealert.core.planningPhase.IPlanningPhaseExposedToDecisionMaker;

public interface IDecisionMaker {
    Runnable allowMakingDecisionsForPlanningPhase(IPlanningPhaseExposedToDecisionMaker planningPhase);
}
