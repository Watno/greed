package spacealert.core.threats.implementations.external.serious.white;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

import java.util.stream.Collectors;

public class LeviathanTanker extends ExternalThreat {
    public LeviathanTanker(Zone zone) {
        super(2, 8, 4, 8, 3, zone);
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        return attack(boardState, 2);
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        heal(2);
        return attack(boardState, 2);
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return attack(boardState, 2);
    }

    @Override
    protected GameLost onDestroyed(BoardState boardState) {
        for (var threat : boardState.getActiveThreats().stream().filter(x -> x instanceof ExternalThreat).collect(Collectors.toList())) {
            var gameLost = threat.takeDamage(boardState, 1);
            if (gameLost == GameLost.TRUE) return GameLost.TRUE;
        }
        return GameLost.FALSE;
    }
}
