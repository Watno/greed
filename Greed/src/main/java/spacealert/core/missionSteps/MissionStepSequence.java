package spacealert.core.missionSteps;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.BoardState;
import spacealert.core.GameLost;

import java.util.List;

public class MissionStepSequence {
    @JsonProperty
    private final List<MissionStep> missionSteps;

    protected MissionStepSequence(List<MissionStep> missionSteps) {
        this.missionSteps = missionSteps;
    }

    public GameLost execute(BoardState boardState) {
        for (MissionStep phase : missionSteps) {
            var gameLost = phase.doExecutionRules(boardState);
            if (gameLost == GameLost.TRUE) {
                System.out.println("Game was lost");
                return GameLost.TRUE;
            }
        }
        System.out.println("Game was won with score " + boardState.score() + ".");
        return GameLost.FALSE;
    }
}
