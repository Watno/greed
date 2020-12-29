package spacealert.core.threats.implementations.external;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class Frigate extends ExternalThreat {
    public Frigate(Zone zone) {
        super(2, 7, 4, 8, 2, zone);
    }

    @Override
    protected GameLost doXAction(Game game) {
        return attack(game, 2);
    }

    @Override
    protected GameLost doYAction(Game game) {
        return attack(game, 3);
    }

    @Override
    protected GameLost doZAction(Game game) {
        return attack(game, 4);
    }
}
