package spacealert.core;

import spacealert.core.locations.ILocation;

import java.util.Optional;

public interface ICrewMember {

    void executeAction(int turn, Game game);

	void delay();

    void moveTo(ILocation newLocation);

    void moveInDirection(Game game, Direction direction);

	ILocation getLocation();

	Optional<Zone> getZone();

    void executeButton(Game game, Button button);

    boolean isInSpace();
}
