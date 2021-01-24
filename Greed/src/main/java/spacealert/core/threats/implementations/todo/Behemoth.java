package spacealert.core.threats.implementations.todo;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.damageSources.Interceptors;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class Behemoth extends ExternalThreat {
    public Behemoth(Zone zone) {
        super(2, 7, 6, 12, 4, zone);
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        if (getDamageTaken() < 2) {
            return attack(boardState, 2);
        }
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        if (getDamageTaken() < 3) {
            return attack(boardState, 3);
        }
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        if (getDamageTaken() < 6) {
            return attack(boardState, 6);
        }
        return GameLost.FALSE;
    }

    @Override
    public void assignDamageTo(BoardState boardState, int damage, DamageSource source) {
        if (source instanceof Interceptors) {
            assignedDamage += 9;
            //TODO knockout
        }
    }
}
