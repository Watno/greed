package spacealert.core.threats.implementations.internal;

import spacealert.core.BoardState;
import spacealert.core.Button;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.Malfunction;

public class UnstableWarheads extends Malfunction {

    public UnstableWarheads() {
        super(3, 3, 2, 4, new Position(Deck.LOWER, Zone.BLUE), Button.C);
    }

    @Override
    protected void onSpawn(BoardState boardState) {
        currentHitPoints = boardState.getAvailableRockets().size();
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return damage(boardState, 3 * currentHitPoints);
    }
}
