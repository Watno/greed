package spacealert.core;

import java.util.Map;
import java.util.Optional;

class StationLayout {
    private Map<Position, ILocation> stations;

    Optional<ILocation> getAdjacentInDirection(ILocation location, Direction direction){
        Optional<Position> positionOfOriginal = location.getPosition();
        if (positionOfOriginal.isEmpty()) return Optional.empty();
        Optional<Position> newPosition = positionOfOriginal.get().getAdjacent(direction);
        if (newPosition.isEmpty()) return Optional.empty();
        return Optional.ofNullable(stations.get(newPosition.get()));
    }
}
