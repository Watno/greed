package spacealert.core.threats.implementations.external;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.damageSources.Rocket;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class Asteroid extends ExternalThreat {
    public Asteroid(Zone zone) {
        super(3, 9, 2, 4, 0, zone);
    }

    private int passedXAndYSquares = 0;

    @Override
    protected GameLost doXAction(Game game) {
        passedXAndYSquares++;
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(Game game) {
        passedXAndYSquares++;
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

    @Override
    protected GameLost onDestroyed(Game game) {
        return attack(game, passedXAndYSquares * 2);
    }
}
