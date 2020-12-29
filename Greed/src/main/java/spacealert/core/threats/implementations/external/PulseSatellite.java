package spacealert.core.threats.implementations.external;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class PulseSatellite extends ExternalThreat {
    public PulseSatellite(Zone zone) {
        super(3, 4, 4, 8, 2, zone);
    }

    @Override
    protected GameLost doXAction(Game game) {
        return attack(game, Zone.all, 1);
    }

    @Override
    protected GameLost doYAction(Game game) {
        return attack(game, Zone.all, 2);
    }

    @Override
    protected GameLost doZAction(Game game) {
        return attack(game, Zone.all, 3);
    }

    @Override
    public boolean canBeTargetedBy(DamageSource damageSource) {
        return getDistance() != 3;
    }
}
