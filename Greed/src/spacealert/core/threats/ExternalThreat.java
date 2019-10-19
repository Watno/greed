package spacealert.core.threats;

import spacealert.core.Game;
import spacealert.core.boardElements.positions.Zone;

public abstract class ExternalThreat extends Threat {
    private int shieldPoints;

    protected ExternalThreat(int speed, int hitPoints) {
        super(speed, hitPoints);
    }

    private Zone zone;


    @Override
    Trajectory getTrajectory(Game game) {
        return game.getTrajectory(zone);
    }
}
