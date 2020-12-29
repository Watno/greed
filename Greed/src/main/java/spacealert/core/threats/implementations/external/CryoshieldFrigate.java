package spacealert.core.threats.implementations.external;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class CryoshieldFrigate extends ExternalThreat {
    public CryoshieldFrigate(Zone zone) {
        super(2, 7, 4, 8, 1, zone);
    }

    @Override
    protected GameLost doXAction(Game game) {
        return attack(game, 2);
    }

    @Override
    protected GameLost doYAction(Game game) {
        return attack(game, 3);
    }

    @Override
    protected GameLost doZAction(Game game) {
        return attack(game, 4);
    }

    @Override
    public boolean canBeTargetedBy(DamageSource damageSource) {
        return getDistance() != 3;
    }

    private boolean hitOnPreviousTurn = false;

    @Override
    public GameLost resolveDamage(Game game) {
        if (hitOnPreviousTurn) {
            return super.resolveDamage(game);
        } else {
            hitOnPreviousTurn = true;
            resetAfterDamageResolution();
            return GameLost.FALSE;
        }
    }
}
