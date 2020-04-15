package spacealert.core.missionSteps;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.ICrewMember;

public class PlayerActionsStep implements IMissionStep {
    private int turn;

    PlayerActionsStep(int turn) {
        super();
        this.turn = turn;
    }

    @Override
    public GameLost execute(Game game) {
        for (ICrewMember crewMember : game.getCrewMembers()) {
            crewMember.executeAction(turn, game);
        }
        game.resetAtEndOfActionPhase();

        return GameLost.FALSE;
    }

}
