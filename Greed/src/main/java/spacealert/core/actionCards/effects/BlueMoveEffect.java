package spacealert.core.actionCards.effects;

import spacealert.core.BoardState;
import spacealert.core.ICrewMemberFromBoardStatePerspective;
import spacealert.core.boardElements.positions.Direction;

public class BlueMoveEffect extends CardEffect {

    protected void executeEffect(ICrewMemberFromBoardStatePerspective crewmember, BoardState boardState) {
        crewmember.moveInDirection(boardState, Direction.BLUE);
    }

}
