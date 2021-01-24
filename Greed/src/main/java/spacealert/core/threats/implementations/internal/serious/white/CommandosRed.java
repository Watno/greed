package spacealert.core.threats.implementations.internal.serious.white;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.ICrewMemberFromBoardStatePerspective;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Direction;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.Intruder;

public class CommandosRed extends Intruder {

    public CommandosRed() {
        super(2, 2, 4, 8, new Position(Deck.LOWER, Zone.RED));
    }

    @Override
    public boolean returnsFire() {
        return true;
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        moveInDirection(boardState, Direction.GRAVOLIFT);
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        if (currentHitPoints != maximumHitPoints) {
            moveInDirection(boardState, Direction.BLUE);
        } else {
            return damage(boardState, 2);
        }
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        var gameLost = damage(boardState, 4);
        if (gameLost == GameLost.TRUE) return GameLost.TRUE;

        locations.stream().flatMap(x -> x.getCrewMembers().stream())
                .forEach(ICrewMemberFromBoardStatePerspective::becomeKnockedOut);
        return GameLost.FALSE;
    }
}
