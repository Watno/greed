package spacealert.core.threats;

import spacealert.core.Game;

public abstract class InternalThreat extends Threat {
    protected InternalThreat(int speed, int hitPoints, int pointsForSurviving, int pointsForDestroying) {
        super(speed, hitPoints, pointsForSurviving, pointsForDestroying);
    }

    @Override
    Trajectory getTrajectory(Game game) {
        return game.getInternalTrajectory();
    }
}
