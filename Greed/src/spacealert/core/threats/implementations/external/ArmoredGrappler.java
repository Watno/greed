package spacealert.core.threats.implementations.external;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class ArmoredGrappler extends ExternalThreat {
    public ArmoredGrappler(Zone zone) {
        super(2, 4, 2, 4, 3, zone);
    }

    @Override
    protected GameLost doXAction(Game game) {
        return attack(game, 1);
    }

    @Override
    protected GameLost doYAction(Game game) {
        heal(1);
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(Game game) {
        return attack(game, 4);
    }
}
