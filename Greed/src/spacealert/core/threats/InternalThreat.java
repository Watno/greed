package spacealert.core.threats;

import spacealert.core.Game;

public abstract class InternalThreat extends Threat {
    protected InternalThreat(int speed, int hitPoints) {
        super(speed, hitPoints);
    }

    @Override
    Trajectory getTrajectory(Game game) {
        return game.getInternalTrajectory();
    }
}
