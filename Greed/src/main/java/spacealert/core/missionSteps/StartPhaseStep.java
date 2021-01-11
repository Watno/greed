package spacealert.core.missionSteps;

import spacealert.core.Game;
import spacealert.core.GameLost;

public class StartPhaseStep implements IMissionStep {
    private int phase;

    StartPhaseStep(int phase) {
        this.phase = phase;
    }

    @Override
    public GameLost execute(Game game) {
        game.startPhase(phase);

        return GameLost.FALSE;
    }
}
