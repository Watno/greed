package spacealert.core.missionSteps;

import spacealert.core.Game;

public class ComputerMaintenanceCheckStep implements IMissionStep {
	ComputerMaintenanceCheckStep() {
		super();
	}

	private int phase;
	private int delayedTurn;
	
	@Override
	public void execute(Game game) {
		if (!game.mouseJuggled()) {
			game.delayAllCrewMembers();
		}
	}

}
