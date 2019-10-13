package spacealert.core.missionSteps;

import spacealert.core.Game;

public class ComputerMaintenanceCheckStep implements IMissionStep {
	public ComputerMaintenanceCheckStep(int phase, int delayedTurn) {
		super();
		this.phase = phase;
		this.delayedTurn = delayedTurn;
	}

	private int phase;
	private int delayedTurn;
	
	@Override
	public void execute(Game game) {
		if (!game.mouseJuggled(phase));
			game.delayAllCrewMembers(delayedTurn);	
	}

}
