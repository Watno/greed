package spacealert.core.missionSteps;

import spacealert.core.Game;
import spacealert.core.GameLost;

public class ComputerMaintenanceCheckStep implements IMissionStep {
    ComputerMaintenanceCheckStep() {
        super();
    }

    @Override
    public GameLost execute(Game game) {
        if (!game.mouseJuggled()) {
            game.delayAllCrewMembers();
        }
        return GameLost.FALSE;
    }

}
