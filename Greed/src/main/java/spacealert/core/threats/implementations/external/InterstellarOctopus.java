package spacealert.core.threats.implementations.external;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.damageSources.Rocket;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class InterstellarOctopus extends ExternalThreat {
    public InterstellarOctopus(Zone zone) {
        super(2, 8, 4, 8, 1, zone);
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        if (currentHitPoints != maximumHitPoints) {
            return attack(boardState, Zone.all, 1);
        }
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        if (currentHitPoints != maximumHitPoints) {
            return attack(boardState, Zone.all, 2);
        }
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return attack(boardState, 2 * currentHitPoints);
    }

    @Override
    public boolean canBeTargetedBy(DamageSource damageSource) {
        return !(damageSource instanceof Rocket);
    }
}
