package spacealert.core.missionSteps;

import spacealert.core.Game;
import spacealert.core.GameLost;

public class ThreatActionsStep implements IMissionStep {

    @Override
    public GameLost execute(Game game) {

        for (var threat : game.getActiveThreats()) {
            var lostByThreat = threat.advance(game);
            if (lostByThreat == GameLost.TRUE) return GameLost.TRUE;
        }
        game.advanceRockets();

        return GameLost.FALSE;
    }

}
