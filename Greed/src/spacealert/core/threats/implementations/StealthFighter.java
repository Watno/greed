package spacealert.core.threats.implementations;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class StealthFighter extends ExternalThreat {
    protected StealthFighter(Zone zone) {
        super(3, 4, 2, 4, 2, zone);
    }

    private boolean revealed;

    @Override
    public boolean canBeTargetedBy(DamageSource damageSource) {
        return revealed;
    }

    @Override
    protected GameLost doXAction(Game game) {
        revealed = true;
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(Game game) {
        return attack(game, 2);
    }

    @Override
    protected GameLost doZAction(Game game) {
        return attack(game, 2);
    }
}
