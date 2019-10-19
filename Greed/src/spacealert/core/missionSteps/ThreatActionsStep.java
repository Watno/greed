package spacealert.core.missionSteps;

import spacealert.core.Game;

public class ThreatActionsStep implements IMissionStep {

	@Override
	public void execute(Game game) {
		for (var threat : game.getThreats()) {
			threat.advance(game);
		}
		game.advanceRockets();
	}

}
