package spacealert.core.threats.implementations;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class Destroyer extends ExternalThreat {
    public Destroyer(Zone zone) {
        super(2, 5, 2, 4, 2, zone);
    }

    @Override
    protected GameLost doXAction(Game game) {
        return attackWithDoubleDamageAfterShields(game, 1);
    }

    @Override
    protected GameLost doYAction(Game game) {
        return attackWithDoubleDamageAfterShields(game, 2);
    }

    @Override
    protected GameLost doZAction(Game game) {
        return attackWithDoubleDamageAfterShields(game, 2);
    }

    private GameLost attackWithDoubleDamageAfterShields(Game game, int amount) {
        var remainingDamage = game.applyShieldsToDamage(this.zone, amount);
        return game.damage(zone, remainingDamage * 2);
    }
}
