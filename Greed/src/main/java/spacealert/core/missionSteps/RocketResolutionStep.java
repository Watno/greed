package spacealert.core.missionSteps;

import spacealert.core.BoardState;
import spacealert.core.GameLost;

public class RocketResolutionStep extends MissionStep {

    @Override
    public GameLost doExecutionRules(BoardState boardState) {
        var rocket = boardState.getCurrentTurnRocket();
        var threats = boardState.getActiveThreats();

        rocket.ifPresent(x -> x.assignDamage(threats, boardState));
        for (var threat : threats) {
            var gameLost = threat.resolveDamage(boardState);
            if (gameLost == GameLost.TRUE) return GameLost.TRUE;
        }
        return GameLost.FALSE;
    }

}
