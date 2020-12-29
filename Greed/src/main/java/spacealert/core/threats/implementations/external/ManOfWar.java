package spacealert.core.threats.implementations.external;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class ManOfWar extends ExternalThreat {
    public ManOfWar(Zone zone) {
        super(1, 9, 4, 8, 2, zone);
    }

    @Override
    protected GameLost doXAction(Game game) {
        speed++;
        return attack(game, 2);
    }

    @Override
    protected GameLost doYAction(Game game) {
        shieldPoints++;
        return attack(game, 3);
    }

    @Override
    protected GameLost doZAction(Game game) {
        return attack(game, 5);
    }
}
