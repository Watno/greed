package spacealert.core.planningPhase;

import spacealert.core.IDecisionMaker;
import spacealert.core.Phase;
import spacealert.core.planningPhase.commands.actionCards.IPlanningPhaseCommand;

public interface IPlanningPhaseExposedToDecisionMaker {
    void execute(IDecisionMaker decisionMaker, IPlanningPhaseCommand command);

    void endPhaseFor(Player player, Phase phase);
}
