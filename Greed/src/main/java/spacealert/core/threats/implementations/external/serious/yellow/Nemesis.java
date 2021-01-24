package spacealert.core.threats.implementations.external.serious.yellow;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class Nemesis extends ExternalThreat {
    public Nemesis(Zone zone) {
        super(3, 9, 6, 12, 1, zone);
    }

    private boolean damageTaken = false;

    @Override
    protected GameLost doXAction(BoardState boardState) {
        takeDamage(boardState, 1);
        return attack(boardState, 1);
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        takeDamage(boardState, 2);
        return attack(boardState, 2);
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        becomeDestroyed(boardState);
        return GameLost.TRUE;
    }

    @Override
    protected GameLost resetAfterDamageResolution(BoardState boardState) {
        if (damageTaken) {
            if (attack(boardState, Zone.all, 1) == GameLost.TRUE) {
                return GameLost.TRUE;
            }
        }
        damageTaken = false;
        return super.resetAfterDamageResolution(boardState);
    }
}
