package spacealert.externalPlayerInterface;

import com.fasterxml.jackson.core.type.TypeReference;
import server.games.IUserFromGamePerspective;
import spacealert.core.IDecisionMaker;
import spacealert.core.planningPhase.IPlanningPhaseExposedToDecisionMaker;
import spacealert.core.planningPhase.commands.actionCards.IPlanningPhaseCommand;

public class ExternalDecisionMaker implements IDecisionMaker {
    private final IUserFromGamePerspective user;

    public ExternalDecisionMaker(IUserFromGamePerspective user) {
        this.user = user;
    }

    @Override
    public Runnable allowMakingDecisionsForPlanningPhase(IPlanningPhaseExposedToDecisionMaker planningPhase) {
        return () -> makeDecisionsForPlanningPhase(planningPhase);
    }

    private void makeDecisionsForPlanningPhase(IPlanningPhaseExposedToDecisionMaker planningPhase) {
        try {
            user.send("Start of planning phase");
            while (!Thread.currentThread().isInterrupted()) {
                var command = user.awaitTypedInput(new TypeReference<IPlanningPhaseCommand>() {
                });
                planningPhase.execute(this, command);
            }
        } catch (InterruptedException e) {
            user.send("End of planning phase during wait");
        }
        user.send("End of planning phase");
    }
}
