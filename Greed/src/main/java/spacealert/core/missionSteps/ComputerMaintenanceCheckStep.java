package spacealert.core.missionSteps;

import spacealert.core.BoardState;
import spacealert.core.GameLost;

public class ComputerMaintenanceCheckStep implements IMissionStep {
    ComputerMaintenanceCheckStep() {
        super();
    }

    @Override
    public GameLost execute(BoardState boardState) {
        if (!boardState.mouseJuggled()) {
            boardState.delayAllCrewMembers();
        }
        return GameLost.FALSE;
    }

}
