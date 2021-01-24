package spacealert.core.threats.implementations.internal.nonserious.white;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.energyBuckets.reactors.Reactor;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Direction;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.Intruder;

public class SaboteurRed extends Intruder {

    public SaboteurRed() {
        super(4, 1, 2, 4, new Position(Deck.LOWER, Zone.WHITE));
    }


    @Override
    protected GameLost doXAction(BoardState boardState) {
        moveInDirection(boardState, Direction.RED);
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        for (var location : this.locations) {
            var energyTaken = location.getZone()
                    .map(boardState::getReactor)
                    .map(Reactor::tryWithdrawOneEnergy)
                    .orElse(false);
            if (!energyTaken) {
                var gameLost = damage(boardState, 1);
                if (gameLost == GameLost.TRUE) return GameLost.TRUE;
            }
        }
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return damage(boardState, 2);
    }
}
