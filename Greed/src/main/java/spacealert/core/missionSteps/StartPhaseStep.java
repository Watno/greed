package spacealert.core.missionSteps;

import spacealert.core.BoardState;
import spacealert.core.GameLost;

public class StartPhaseStep extends MissionStep {
    private final int phase;

    StartPhaseStep(int phase) {
        this.phase = phase;
    }

    @Override
    public GameLost doExecutionRules(BoardState boardState) {
        boardState.startPhase(phase);

        return GameLost.FALSE;
    }
}
