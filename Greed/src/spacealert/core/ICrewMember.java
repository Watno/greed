package spacealert.core;

import spacealert.core.locations.ILocation;

import java.util.Optional;

public interface ICrewMember {

	void executeAction(int turn);

	void delay();

    void moveTo(ILocation newLocation);

    void moveInDirection(Direction direction);

	ILocation getLocation();

	Optional<Zone> getZone();

    void executeButton(Button button);

    boolean isInSpace();
}
