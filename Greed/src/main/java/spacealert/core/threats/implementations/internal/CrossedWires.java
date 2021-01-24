package spacealert.core.threats.implementations.internal;

import spacealert.core.BoardState;
import spacealert.core.Button;
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
    protected GameLost doXAction(BoardState boardState) {
        boardState.getShield(Zone.WHITE).chargeFrom(boardState.getReactor(Zone.WHITE));
        var drainedEnergy = boardState.getReactor(Zone.WHITE).drain();
        return boardState.damage(Zone.WHITE, drainedEnergy);
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        var drainedEnergy = boardState.getShield(Zone.WHITE).drain();
        return boardState.damage(Zone.WHITE, drainedEnergy);
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        for (var zone : Zone.values()) {
            var drainedEnergy = boardState.getReactor(zone).drain();
            var gameLost = boardState.damage(zone, drainedEnergy);
            if (gameLost == GameLost.TRUE) return GameLost.TRUE;
        }
        return GameLost.FALSE;
    }
}
