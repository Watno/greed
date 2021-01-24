package spacealert.core.missionSteps;

import spacealert.core.BoardState;
import spacealert.core.GameLost;

import java.util.List;

public class MissionStepSequence {
    private final List<IMissionStep> missionSteps;

    protected MissionStepSequence(List<IMissionStep> missionSteps) {
        this.missionSteps = missionSteps;
    }

    public GameLost execute(BoardState boardState) {
        for (IMissionStep phase : missionSteps) {
            var gameLost = phase.execute(boardState);
            if (gameLost == GameLost.TRUE) {
                System.out.println("Game was lost");
                return GameLost.TRUE;
            }
        }
        System.out.println("Game was won with score " + boardState.score() + ".");
        return GameLost.FALSE;
    }
}
