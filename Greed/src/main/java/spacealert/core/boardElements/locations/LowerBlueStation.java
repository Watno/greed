package spacealert.core.boardElements.locations;

import spacealert.core.BoardState;
import spacealert.core.ICrewMemberFromBoardStatePerspective;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;

public class LowerBlueStation extends Location {
    LowerBlueStation() {
        super(new Position(Deck.LOWER, Zone.BLUE));
    }

    @Override
    protected void executeBButton(BoardState boardState, ICrewMemberFromBoardStatePerspective crewMember) {
        boardState.getReactor(Zone.BLUE).chargeFrom(boardState.getReactor(Zone.WHITE));
    }

    @Override
    protected void executeCButton(BoardState boardState, ICrewMemberFromBoardStatePerspective crewMember) {
        boardState.tryLaunchRocket();
    }
}
