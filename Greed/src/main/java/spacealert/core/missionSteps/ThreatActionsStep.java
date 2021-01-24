package spacealert.core.missionSteps;

import spacealert.core.BoardState;
import spacealert.core.GameLost;

public class ThreatActionsStep implements IMissionStep {

    @Override
    public GameLost execute(BoardState boardState) {

        for (var threat : boardState.getActiveThreats()) {
            var lostByThreat = threat.advance(boardState);
            if (lostByThreat == GameLost.TRUE) return GameLost.TRUE;
        }
        boardState.advanceRockets();

        return GameLost.FALSE;
    }

}
