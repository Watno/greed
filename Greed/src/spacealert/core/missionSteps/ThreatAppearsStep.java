package spacealert.core.missionSteps;

import spacealert.core.Game;
import spacealert.core.GameLost;

public class ThreatAppearsStep implements IMissionStep {

    protected ThreatAppearsStep() {
        super();
    }

    @Override
    public GameLost execute(Game game) {
        return game.spawnThreats();
    }

}
