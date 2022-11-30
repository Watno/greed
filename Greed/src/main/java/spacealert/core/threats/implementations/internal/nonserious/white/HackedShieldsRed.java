package spacealert.core.threats.implementations.internal.nonserious.white;

import spacealert.core.BoardState;
import spacealert.core.Button;
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
    protected GameLost doXAction(BoardState boardState) {
        for (var location : this.locations) {
            location.getZone()
                    .map(boardState::getShield)
                    .ifPresent(EnergyBucket::drain);
        }
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        boardState.getReactor(Zone.RED).drain();
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return damage(boardState, 2);
    }
}