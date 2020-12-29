package spacealert.core.threats.implementations.external;

import spacealert.core.Game;
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
    protected GameLost doXAction(Game game) {
        if (currentHitPoints != maximumHitPoints) {
            return attack(game, Zone.all, 1);
        }
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(Game game) {
        if (currentHitPoints != maximumHitPoints) {
            return attack(game, Zone.all, 2);
        }
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(Game game) {
        return attack(game, 2 * currentHitPoints);
    }

    @Override
    public boolean canBeTargetedBy(DamageSource damageSource) {
        return !(damageSource instanceof Rocket);
    }
}
