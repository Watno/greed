package spacealert.core.missionSteps;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.ICrewMemberFromBoardStatePerspective;

public class PlayerActionsStep extends MissionStep {
    @JsonProperty
    private final int turn;

    PlayerActionsStep(int turn) {
        super();
        this.turn = turn;
    }

    @Override
    public GameLost doExecutionRules(BoardState boardState) {
        for (ICrewMemberFromBoardStatePerspective crewMember : boardState.getCrewMembers()) {
            crewMember.executeAction(turn, boardState);
        }
        boardState.resetAtEndOfActionPhase();

        return GameLost.FALSE;
    }

}
