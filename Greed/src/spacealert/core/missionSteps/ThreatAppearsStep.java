package spacealert.core.missionSteps;

import spacealert.core.Game;

public class ThreatAppearsStep implements IMissionStep {

    protected ThreatAppearsStep() {
		super();
	}

	@Override
	public void execute(Game game) {
        game.spawnThreats();
	}

}
