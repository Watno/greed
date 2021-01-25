package spacealert.core.missionSteps;

import spacealert.core.BoardState;
import spacealert.core.GameLost;

public class ComputeDamageStep extends MissionStep {

    @Override
    public GameLost doExecutionRules(BoardState boardState) {
        var damageSources = boardState.getDamageSources();
        var threats = boardState.getActiveThreats();

        for (var damageSource : damageSources) {
            damageSource.assignDamage(threats, boardState);
        }
        for (var threat : threats) {
            var gameLost = threat.resolveDamage(boardState);
            if (gameLost == GameLost.TRUE) return GameLost.TRUE;
        }
        return GameLost.FALSE;
    }

}
