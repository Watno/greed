package spacealert.core.actionCards.effects;

import spacealert.core.BoardState;
import spacealert.core.Button;
import spacealert.core.ICrewMemberFromBoardStatePerspective;

public class CEffect extends CardEffect {

    protected void executeEffect(ICrewMemberFromBoardStatePerspective crewmember, BoardState boardState) {
        crewmember.executeButton(boardState, Button.C);
    }
}
