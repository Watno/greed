package spacealert.core.threats.implementations.internal;

import spacealert.core.Button;
import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.Malfunction;

public class CrossedWires extends Malfunction {
    public CrossedWires() {
        super(3, 4, 4, 8, new Position(Deck.UPPER, Zone.WHITE), Button.B);
    }

    @Override
    protected GameLost doXAction(Game game) {
        game.getShield(Zone.WHITE).chargeFrom(game.getReactor(Zone.WHITE));
        var drainedEnergy = game.getReactor(Zone.WHITE).drain();
        return game.damage(Zone.WHITE, drainedEnergy);
    }

    @Override
    protected GameLost doYAction(Game game) {
        var drainedEnergy = game.getShield(Zone.WHITE).drain();
        return game.damage(Zone.WHITE, drainedEnergy);
    }

    @Override
    protected GameLost doZAction(Game game) {
        for (var zone : Zone.values()) {
            var drainedEnergy = game.getReactor(zone).drain();
            var gameLost = game.damage(zone, drainedEnergy);
            if (gameLost == GameLost.TRUE) return GameLost.TRUE;
        }
        return GameLost.FALSE;
    }
}
