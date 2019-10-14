package spacealert.core;

import spacealert.core.locations.*;

import java.util.Map;
import java.util.Optional;

public class StationLayout {
    private Map<Position, ILocation> stations;

    public StationLayout(Game game) {
        stations = Map.of(
                new Position(Deck.UPPER, Zone.RED), new UpperRedStation(game),
                new Position(Deck.UPPER, Zone.WHITE), new UpperWhiteStation(game),
                new Position(Deck.UPPER, Zone.BLUE), new UpperBlueStation(game),
                new Position(Deck.LOWER, Zone.RED), new LowerRedStation(game),
                new Position(Deck.LOWER, Zone.WHITE), new LowerWhiteStation(game),
                new Position(Deck.LOWER, Zone.BLUE), new LowerBlueStation(game)
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
