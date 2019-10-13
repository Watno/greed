package spacealert.core;

import java.util.Optional;

public interface ICrewMember {

	void executeAction(int turn);

	void delay();

    void moveInDirection(Direction direction);

	ILocation getLocation();

	Optional<Zone> getZone();

    void executeButton(Button button);
}
