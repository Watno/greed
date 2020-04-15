package spacealert.core.threats.implementations;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.damageSources.Rocket;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class Amoeba extends ExternalThreat {
    public Amoeba(Zone zone) {
        super(2, 8, 2, 4, 0, zone);
    }

    @Override
    protected GameLost doXAction(Game game) {
        heal(2);
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(Game game) {
        heal(2);
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(Game game) {
        return attack(game, 5);
    }

    @Override
    public boolean canBeTargetedBy(DamageSource damageSource) {
        return !(damageSource instanceof Rocket);
    }
}
