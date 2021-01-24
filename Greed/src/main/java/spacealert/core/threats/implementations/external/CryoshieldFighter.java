package spacealert.core.threats.implementations.external;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class CryoshieldFighter extends ExternalThreat {
    public CryoshieldFighter(Zone zone) {
        super(3, 4, 2, 4, 1, zone);
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        return attack(boardState, 1);
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        return attack(boardState, 2);
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return attack(boardState, 2);
    }

    private boolean hitOnPreviousTurn = false;

    @Override
    public GameLost resolveDamage(BoardState boardState) {
        if (hitOnPreviousTurn) {
            return super.resolveDamage(boardState);
        } else {
            hitOnPreviousTurn = true;
            resetAfterDamageResolution();
            return GameLost.FALSE;
        }
    }
}
