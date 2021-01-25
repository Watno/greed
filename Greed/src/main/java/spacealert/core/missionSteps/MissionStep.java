package spacealert.core.missionSteps;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.BoardState;
import spacealert.core.GameLost;

public abstract class MissionStep {
    @JsonProperty
    private ExecutionState executionState = ExecutionState.NOTEXECUTED;

    public GameLost executea(BoardState boardState) {
        executionState = ExecutionState.INEXECUTION;
        if (doExecutionRules(boardState) == GameLost.TRUE) return GameLost.TRUE;
        executionState = ExecutionState.EXECUTED;
        return GameLost.FALSE;
    }

    protected abstract GameLost doExecutionRules(BoardState boardState);
}

