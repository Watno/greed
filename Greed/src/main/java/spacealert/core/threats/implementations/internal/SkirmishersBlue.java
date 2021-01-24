package spacealert.core.threats.implementations.internal;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Direction;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.Intruder;

public class SkirmishersBlue extends Intruder {

    public SkirmishersBlue() {
        super(3, 1, 2, 4, new Position(Deck.UPPER, Zone.BLUE));
    }

    @Override
    public boolean returnsFire() {
        return true;
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        moveInDirection(boardState, Direction.RED);
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        moveInDirection(boardState, Direction.GRAVOLIFT);
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return damage(boardState, 3);
    }
}
