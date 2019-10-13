package spacealert.core.missionSteps;

import spacealert.core.Game;

public class ComputerMaintenanceCheckStep implements IMissionStep {
	public ComputerMaintenanceCheckStep(int phase) {
		super();
		this.phase = phase;
	}

	private int phase;
	private int delayedTurn;
	
	@Override
	public void execute(Game game) {
		if (!game.mouseJuggled(phase)) {
			game.delayAllCrewMembers();
		}
	}

}
