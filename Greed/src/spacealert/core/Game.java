package spacealert.core;

import java.util.Collection;
import java.util.Map;

public class Game {
	private Collection<ICrewMember> crewMembers;
	private StationLayout stationLayout;
	private Map<Zone, Gravolift> gravolifts;

	public Collection<ICrewMember> getCrewMembers() {
		return crewMembers;
	}
	
	public boolean mouseJuggled(int phase) {
		return true;
	}
	
	public void delayAllCrewMembers() {
		for(ICrewMember crewMember: crewMembers) {
			crewMember.delay();
		}
	}

	public StationLayout getStationLayout() {
		return stationLayout;
	}

	public Map<Zone, Gravolift> getGravolifts() {
		return gravolifts;
	}
}
