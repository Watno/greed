package spacealert.core.threats.templates;

import spacealert.core.boardElements.positions.Position;

import java.util.List;

public abstract class Intruder extends InternalThreat {
    protected Intruder(int speed, int hitPoints, int pointsForSurviving, int pointsForDestroying, List<Position> spawnPositions) {
        super(speed, hitPoints, pointsForSurviving, pointsForDestroying, spawnPositions);
    }

    protected Intruder(int speed, int hitPoints, int pointsForSurviving, int pointsForDestroying, Position spawnPosition) {
        this(speed, hitPoints, pointsForSurviving, pointsForDestroying, List.of(spawnPosition));
    }

    @Override
    public boolean canBeTargetedByBattlebot() {
        return true;
    }

}
