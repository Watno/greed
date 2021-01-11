package spacealert.core.threats.implementations.external;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

import java.util.stream.Collectors;

public class LeviathanTanker extends ExternalThreat {
    public LeviathanTanker(Zone zone) {
        super(2, 8, 4, 8, 3, zone);
    }

    @Override
    protected GameLost doXAction(Game game) {
        return attack(game, 2);
    }

    @Override
    protected GameLost doYAction(Game game) {
        heal(2);
        return attack(game, 2);
    }

    @Override
    protected GameLost doZAction(Game game) {
        return attack(game, 2);
    }

    @Override
    protected GameLost onDestroyed(Game game) {
        for (var threat : game.getActiveThreats().stream().filter(x -> x instanceof ExternalThreat).collect(Collectors.toList())) {
            var gameLost = threat.takeDamage(game, 1);
            if (gameLost == GameLost.TRUE) return GameLost.TRUE;
        }
        return GameLost.FALSE;
    }
}
