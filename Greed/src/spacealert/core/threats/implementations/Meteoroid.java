package spacealert.core.threats.implementations;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.damageSources.Rocket;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class Meteoroid extends ExternalThreat {
    public Meteoroid(Zone zone) {
        super(5, 5, 2, 4, 0, zone);
    }

    @Override
    protected GameLost doXAction(Game game) {
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(Game game) {
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(Game game) {
        return attack(game, currentHitPoints);
    }

    @Override
    public boolean canBeTargetedBy(DamageSource damageSource) {
        return !(damageSource instanceof Rocket);
    }
}
