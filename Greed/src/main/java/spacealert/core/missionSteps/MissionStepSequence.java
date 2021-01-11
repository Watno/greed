package spacealert.core.missionSteps;

import spacealert.core.Game;
import spacealert.core.GameLost;

import java.util.List;

public class MissionStepSequence {
    private List<IMissionStep> missionSteps;

    protected MissionStepSequence(List<IMissionStep> missionSteps) {
        super();
        this.missionSteps = missionSteps;
    }

    public GameLost execute(Game game) {
        for (IMissionStep phase : missionSteps) {
            var gameLost = phase.execute(game);
            if (gameLost == GameLost.TRUE) return GameLost.TRUE;
        }
        return GameLost.FALSE;
    }
}
