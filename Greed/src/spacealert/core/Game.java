package spacealert.core;

import spacealert.core.actionCards.ActionBoard;
import spacealert.core.locations.*;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Game {
	private Collection<ICrewMember> crewMembers;
	private StationLayout stationLayout;
	private Map<Zone, Gravolift> gravolifts;
	private Map<Zone, Reactor> reactors;
	private Map<Zone, Shield> shields;
	private Space space;

	public Game() {
		gravolifts = Map.of(
				Zone.RED, new Gravolift(),
				Zone.WHITE, new Gravolift(),
				Zone.BLUE, new Gravolift()
		);
		reactors = Map.of(
				Zone.RED, new LateralReactor(),
				Zone.WHITE, new CentralReactor(),
				Zone.BLUE, new LateralReactor()
		);
		shields = Map.of(
				Zone.RED, new LateralShield(),
				Zone.WHITE, new CentralShield(),
				Zone.BLUE, new LateralShield()
		);

		space = new Space();
		stationLayout = new StationLayout();
	}

	public Game(Collection<ActionBoard> actionBoards) {
		this();

		crewMembers = actionBoards.stream()
				.map(x -> new CrewMember(stationLayout.getStation(new Position(Deck.UPPER, Zone.WHITE)), x))
				.collect(Collectors.toList());

		for (var crewMember : crewMembers) {
			var x = stationLayout.getStation(new Position(Deck.UPPER, Zone.WHITE));
			x.addCrewMember(crewMember);
		}
	}


	public Collection<ICrewMember> getCrewMembers() {
		return crewMembers;
	}
	
	public boolean mouseJuggled(int phase) {
		//TODO
		return true;
	}
	
	public void delayAllCrewMembers() {
		for(ICrewMember crewMember: crewMembers) {
			crewMember.delay();
		}
	}

	public ILocation getStation(Position position) {
		return stationLayout.getStation(position);
	}

	public Gravolift getGravolift(Zone zone) {
		return gravolifts.get(zone);
	}

	public Reactor getReactor(Zone zone) {
		return reactors.get(zone);
	}

	public Shield getShield(Zone zone) {
		return shields.get(zone);
	}

	Optional<ILocation> getAdjacentInDirection(ILocation location, Direction direction) {
		return stationLayout.getAdjacentInDirection(location, direction);
	}
}
