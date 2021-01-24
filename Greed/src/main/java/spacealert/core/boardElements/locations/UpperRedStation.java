package spacealert.core.boardElements.locations;

import spacealert.core.BoardState;
import spacealert.core.ICrewMemberFromBoardStatePerspective;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;

public class UpperRedStation extends Location {

    UpperRedStation() {
        super(new Position(Deck.UPPER, Zone.RED));
    }

    @Override
    protected void executeBButton(BoardState boardState, ICrewMemberFromBoardStatePerspective crewMember) {
        boardState.getShield(Zone.RED).chargeFrom(boardState.getReactor(Zone.RED));
    }

    @Override
    protected void executeCButton(BoardState boardState, ICrewMemberFromBoardStatePerspective crewMember) {
        if (crewMember.hasActiveBattlebot()) {
            crewMember.moveTo(boardState.getSpace());
        }
    }
}
