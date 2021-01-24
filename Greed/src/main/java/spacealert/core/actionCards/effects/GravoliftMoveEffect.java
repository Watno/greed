package spacealert.core.actionCards.effects;

import spacealert.core.BoardState;
import spacealert.core.ICrewMemberFromBoardStatePerspective;
import spacealert.core.boardElements.positions.Direction;

public class GravoliftMoveEffect extends CardEffect {

    protected void executeEffect(ICrewMemberFromBoardStatePerspective crewmember, BoardState boardState) {
        crewmember.moveInDirection(boardState, Direction.GRAVOLIFT);
        crewmember.getZone().ifPresent(x -> boardState.getGravolift(x).getUsedBy(crewmember));
    }
}
