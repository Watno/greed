package spacealert.core.missionSteps;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.ICrewMemberFromBoardStatePerspective;

public class PlayerActionsStep implements IMissionStep {
    private final int turn;

    PlayerActionsStep(int turn) {
        super();
        this.turn = turn;
    }

    @Override
    public GameLost execute(BoardState boardState) {
        for (ICrewMemberFromBoardStatePerspective crewMember : boardState.getCrewMembers()) {
            crewMember.executeAction(turn, boardState);
        }
        boardState.resetAtEndOfActionPhase();

        return GameLost.FALSE;
    }

}
