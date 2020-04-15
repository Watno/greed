package spacealert.core.threats.implementations;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class PulseBall extends ExternalThreat {
    public PulseBall(Zone zone) {
        super(2, 5, 2, 4, 1, zone);
    }

    @Override
    protected GameLost doXAction(Game game) {
        return attack(game, Zone.all, 1);
    }

    @Override
    protected GameLost doYAction(Game game) {
        return attack(game, Zone.all, 1);
    }

    @Override
    protected GameLost doZAction(Game game) {
        return attack(game, Zone.all, 2);
    }
}
