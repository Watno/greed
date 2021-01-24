package spacealert.core.threats.implementations.internal;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Direction;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.Intruder;

public class Alien extends Intruder {

    public Alien() {
        super(2, 2, 0, 8, new Position(Deck.LOWER, Zone.WHITE));
    }

    private boolean grownUp = false;

    @Override
    public boolean returnsFire() {
        return grownUp;
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        grownUp = true;
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        moveInDirection(boardState, Direction.GRAVOLIFT);
        return damage(boardState, locations.stream().mapToInt(x -> x.getCrewMembers().size()).sum());
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return GameLost.TRUE;
    }
}
