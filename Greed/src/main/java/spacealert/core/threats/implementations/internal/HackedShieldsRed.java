package spacealert.core.threats.implementations.internal;

import spacealert.core.Button;
import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.energyBuckets.EnergyBucket;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.Malfunction;

public class HackedShieldsRed extends Malfunction {
    public HackedShieldsRed() {
        super(2, 3, 2, 4, new Position(Deck.UPPER, Zone.RED), Button.B);
    }

    @Override
    protected GameLost doXAction(Game game) {
        for (var location : this.locations) {
            location.getZone()
                    .map(game::getShield)
                    .ifPresent(EnergyBucket::drain);
        }
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(Game game) {
        game.getReactor(Zone.RED).drain();
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(Game game) {
        return damage(game, 2);
    }
}
