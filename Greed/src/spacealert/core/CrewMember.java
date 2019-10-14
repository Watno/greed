package spacealert.core;

import spacealert.core.actionCards.ActionBoard;
import spacealert.core.locations.ILocation;

import java.util.Optional;

public class CrewMember implements ICrewMember{
	private ActionBoard actionBoard;
	private ILocation location;
	private Game game;
	
	@Override
	public void executeAction(int turn) {
		actionBoard.execute(turn, this, game);
	}
	
	@Override
	public void delay() {
		actionBoard.delay();
	}

	@Override
	public void moveTo(ILocation newLocation) {
		location.removeCrewMember(this);
		newLocation.addCrewMember(this);
		location = newLocation;
	}

	public void moveInDirection(Direction direction){
		var newPosition = game.getAdjacentInDirection(location, direction);
		newPosition.ifPresent(this::moveTo);

	}

	@Override
	public ILocation getLocation() {
		return location;
	}

	public Optional<Zone> getZone(){
		return location.getZone();
	}

	@Override
	public void executeButton(Button button){
		location.executeButton(this, button);
	}

	@Override
	public boolean isInSpace() {
		return location.isSpace();
	}
}
