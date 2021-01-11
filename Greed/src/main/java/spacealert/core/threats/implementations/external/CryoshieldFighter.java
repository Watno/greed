package spacealert.core.threats.implementations.external;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class CryoshieldFighter extends ExternalThreat {
    public CryoshieldFighter(Zone zone) {
        super(3, 4, 2, 4, 1, zone);
    }

    @Override
    protected GameLost doXAction(Game game) {
        return attack(game, 1);
    }

    @Override
    protected GameLost doYAction(Game game) {
        return attack(game, 2);
    }

    @Override
    protected GameLost doZAction(Game game) {
        return attack(game, 2);
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
