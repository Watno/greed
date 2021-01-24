package spacealert.core.threats.templates;

import spacealert.core.BoardState;
import spacealert.core.boardElements.positions.Direction;
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

    public void moveInDirection(BoardState boardState, Direction direction) {
        for (var location : locations) {
            var newLocation = boardState.getAdjacentInDirection(location, direction);
            if (newLocation.isPresent()) {
                location.removeInternalThreat(this);
                newLocation.get().addInternalThreat(this);
                locations.remove(location);
                locations.add(newLocation.get());
            }
        }
    }

}
