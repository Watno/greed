package spacealert.core.missionSteps;

import spacealert.core.Game;

public class StartPhaseStep implements IMissionStep {
    private int phase;

    StartPhaseStep(int phase) {
        this.phase = phase;
    }

    @Override
    public void execute(Game game) {
        game.startPhase(phase);
    }
}
