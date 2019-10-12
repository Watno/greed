package spacealert.core;

import java.util.Collection;

public class Game {
	private Collection<ICrewMember> crewMembers;

	public Collection<ICrewMember> getCrewMembers() {
		return crewMembers;
	}
	
	public boolean mouseJuggled(int phase) {
		return true;
	}
}
