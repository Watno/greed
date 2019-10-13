package spacealert.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public abstract class Location implements ILocation{
	private Collection<ICrewMember> crewMembers = new ArrayList<>();
	private Optional<Position> position;

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

	}
}
