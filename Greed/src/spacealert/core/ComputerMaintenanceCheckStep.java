package spacealert.core;

public class ComputerMaintenanceCheckStep implements IMissionStep {
	public ComputerMaintenanceCheckStep(int phase) {
		super();
		this.phase = phase;
	}

	private int phase;
	
	@Override
	public void execute(Game game) {
		if (!game.mouseJuggled(phase));
		for (ICrewMember crewMember: game.getCrewMembers()) {
			crewMember
		}
		
	}

}
