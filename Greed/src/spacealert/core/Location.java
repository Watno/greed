package spacealert.core;

import java.util.ArrayList;
import java.util.Collection;

public class Location {
	private Collection<ICrewMember> crewMembers = new ArrayList<ICrewMember>();

	public Collection<ICrewMember> getCrewMembers() {
		return crewMembers;
	}

	public void addCrewMember(ICrewMember crewMember) {
		crewMembers.add(crewMember);
	}
	
	public void removeCrewMember(ICrewMember crewMember) {
		var wasPresent = crewMembers.remove(crewMember);
		if (!wasPresent) {throw new Invalid
	}
}
