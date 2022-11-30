package spacealert.externalPlayerInterface;

import com.fasterxml.jackson.core.type.TypeReference;
import server.games.DisconnectedException;
import server.games.IUserFromGamePerspective;
import spacealert.core.GameResult;
import spacealert.core.IDecisionMaker;
import spacealert.core.gamestates.GameStateWithPrivateInfo;
import spacealert.core.planningPhase.IPlanningPhaseExposedToDecisionMaker;
import spacealert.core.planningPhase.commands.actionCards.IPlanningPhaseCommand;
import spacealert.core.planningPhase.eventSequences.events.Notification;

public class ExternalDecisionMaker implements IDecisionMaker {
    private final IUserFromGamePerspective user;

    public ExternalDecisionMaker(IUserFromGamePerspective user) {
        this.user = user;
    }

    @Override
    public Runnable allowMakingDecisionsForPlanningPhase(IPlanningPhaseExposedToDecisionMaker planningPhase) {
        return () -> makeDecisionsForPlanningPhase(planningPhase);
    }

    @Override
    public void sendGameState(GameStateWithPrivateInfo gameState) {
        user.send(gameState);
    }

    @Override
    public void sendNotification(Notification notification) {
        user.send(notification);
    }

    @Override
    public void sendResult(GameResult result) {
        user.send(result);
    }

    private void makeDecisionsForPlanningPhase(IPlanningPhaseExposedToDecisionMaker planningPhase) {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                var command = user.awaitTypedInput(new TypeReference<IPlanningPhaseCommand>() {});
                planningPhase.execute(this, command);
            }
        } catch (InterruptedException e) {
            //planning phase ended during wait
        } catch (DisconnectedException e) {
            System.out.println("disconnected");
        }
    }
}
