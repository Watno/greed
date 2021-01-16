package spacealert.core.threats.implementations.internal;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.energyBuckets.reactors.Reactor;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Direction;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.Intruder;

public class SaboteurBlue extends Intruder {

    public SaboteurBlue() {
        super(4, 1, 2, 4, new Position(Deck.LOWER, Zone.WHITE));
    }


    @Override
    protected GameLost doXAction(Game game) {
        moveInDirection(game, Direction.BLUE);
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(Game game) {
        for (var location : this.locations) {
            var energyTaken = location.getZone()
                    .map(game::getReactor)
                    .map(Reactor::tryWithdrawOneEnergy)
                    .orElse(false);
            if (!energyTaken) {
                var gameLost = damage(game, 1);
                if (gameLost == GameLost.TRUE) return GameLost.TRUE;
            }
        }
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(Game game) {
        return damage(game, 2);
    }
}