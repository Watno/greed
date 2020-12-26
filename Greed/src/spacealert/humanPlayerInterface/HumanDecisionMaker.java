package spacealert.humanPlayerInterface;

import com.fasterxml.jackson.core.type.TypeReference;
import server.IUserFromGamePerspective;
import spacealert.core.IDecisionMaker;
import spacealert.core.planningPhase.IPlanningPhaseExposedToDecisionMaker;
import spacealert.core.planningPhase.IPlayerExposedToDecisionMaker;
import spacealert.humanPlayerInterface.commands.actionCards.IPlanningPhaseCommand;

public class HumanDecisionMaker implements IDecisionMaker {
    private IUserFromGamePerspective user;


    @Override
    public Runnable allowMakingDecisionsForPlanningPhase(IPlanningPhaseExposedToDecisionMaker planningPhase, IPlayerExposedToDecisionMaker player) {
        return () -> {
            while (true) {
                var command = user.awaitTypedInput(new TypeReference<IPlanningPhaseCommand>() {
                });
                command.execute(planningPhase, player);
            }
        };
    }
}
