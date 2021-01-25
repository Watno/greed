package spacealert.core.missionSteps;

import spacealert.core.BoardState;
import spacealert.core.GameLost;

public class ComputerMaintenanceCheckStep extends MissionStep {
    ComputerMaintenanceCheckStep() {
        super();
    }

    @Override
    public GameLost doExecutionRules(BoardState boardState) {
        if (!boardState.mouseJuggled()) {
            boardState.delayAllCrewMembers();
        }
        return GameLost.FALSE;
    }

}
