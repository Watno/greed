package spacealert.core.boardElements.locations;

import spacealert.core.BoardState;
import spacealert.core.ICrewMemberFromBoardStatePerspective;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;

public class UpperWhiteStation extends Location {

    UpperWhiteStation() {
        super(new Position(Deck.UPPER, Zone.WHITE));
    }

    @Override
    protected void executeBButton(BoardState boardState, ICrewMemberFromBoardStatePerspective crewMember) {
        boardState.getShield(Zone.WHITE).chargeFrom(boardState.getReactor(Zone.WHITE));
    }

    @Override
    protected void executeCButton(BoardState boardState, ICrewMemberFromBoardStatePerspective crewMember) {
        boardState.juggleMouse();
    }
}
