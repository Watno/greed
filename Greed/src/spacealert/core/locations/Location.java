package spacealert.core.locations;

import spacealert.core.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public abstract class Location implements ILocation{
	private Collection<ICrewMember> crewMembers = new ArrayList<>();
	protected Optional<Position> position;
	protected Game game;

	protected Location(Game game) {
		this.game = game;
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
	public void executeButton(ICrewMember crewMember, Button button){
		switch (button) {
			case A:
				executeAButton(crewMember);
				break;
			case B:
				executeBButton(crewMember);
				break;
			case C:
				executeCButton(crewMember);
				break;
		}
	}

	protected abstract void executeAButton(ICrewMember crewMember);

	protected abstract void executeBButton(ICrewMember crewMember);

	protected abstract void executeCButton(ICrewMember crewMember);
}
