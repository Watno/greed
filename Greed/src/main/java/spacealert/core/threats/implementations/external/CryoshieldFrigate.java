package spacealert.core.threats.implementations.external;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class CryoshieldFrigate extends ExternalThreat {
    public CryoshieldFrigate(Zone zone) {
        super(2, 7, 4, 8, 1, zone);
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        return attack(boardState, 2);
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        return attack(boardState, 3);
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return attack(boardState, 4);
    }

    @Override
    public boolean canBeTargetedBy(DamageSource damageSource) {
        return getDistance() != 3;
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
