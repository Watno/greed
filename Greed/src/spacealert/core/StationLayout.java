package spacealert.core;

import spacealert.core.locations.*;

import java.util.Map;
import java.util.Optional;

public class StationLayout {
    private Map<Position, ILocation> stations;

    public StationLayout() {
        stations = Map.of(
                new Position(Deck.UPPER, Zone.RED), new UpperRedStation(),
                new Position(Deck.UPPER, Zone.WHITE), new UpperWhiteStation(),
                new Position(Deck.UPPER, Zone.BLUE), new UpperBlueStation(),
                new Position(Deck.LOWER, Zone.RED), new LowerRedStation(),
                new Position(Deck.LOWER, Zone.WHITE), new LowerWhiteStation(),
                new Position(Deck.LOWER, Zone.BLUE), new LowerBlueStation()
        );
    }

    Optional<ILocation> getAdjacentInDirection(ILocation location, Direction direction){
        Optional<Position> positionOfOriginal = location.getPosition();
        if (positionOfOriginal.isEmpty()) return Optional.empty();
        Optional<Position> newPosition = positionOfOriginal.get().getAdjacent(direction);
        if (newPosition.isEmpty()) return Optional.empty();
        return Optional.ofNullable(stations.get(newPosition.get()));
    }

    public ILocation getStation(Position position) {
        return stations.get(position);
    }
}
