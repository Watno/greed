package spacealert.core.threats.implementations;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class Fighter extends ExternalThreat {
    public Fighter(Zone zone) {
        super(3, 4, 2, 4, 2, zone);
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
        return attack(game, 3);
    }
}
