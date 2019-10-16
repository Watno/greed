package spacealert.core.locations;

import spacealert.core.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public abstract class Location implements ILocation{
	private Collection<ICrewMember> crewMembers = new ArrayList<>();
	private Optional<Position> position;

	protected Location(Position position) {
		this(Optional.of(position));
	}

	protected Location(Optional<Position> position) {
		this.position = position;
	}

	@Override
	public boolean isSpace() {
		return false;
	}

	@Override
	public Collection<ICrewMember> getCrewMembers() {
		return crewMembers;
	}

	@Override
	public Optional<Position> getPosition() {
		return position;
	}

	@Override
	public void addCrewMember(ICrewMember crewMember) {
		crewMembers.add(crewMember);
	}
	
	@Override
	public void removeCrewMember(ICrewMember crewMember) {
		var wasPresent = crewMembers.remove(crewMember);
		if (!wasPresent) {throw new IllegalStateException("expected CrewMember is not here.");}
	}

	public Optional<Zone> getZone(){
		return position.map(Position::getZone);
	}

	@Override
	public void executeButton(Game game, ICrewMember crewMember, Button button) {
		switch (button) {
			case A:
				executeAButton(game, crewMember);
				break;
			case B:
				executeBButton(game, crewMember);
				break;
			case C:
				executeCButton(game, crewMember);
				break;
		}
	}

	protected abstract void executeAButton(Game game, ICrewMember crewMember);

	protected abstract void executeBButton(Game game, ICrewMember crewMember);

	protected abstract void executeCButton(Game game, ICrewMember crewMember);
}
