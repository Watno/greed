package spacealert.core.missionSteps;

import spacealert.core.Game;
import spacealert.core.GameLost;

public class StartTurnStep implements IMissionStep {
    private int turn;

    StartTurnStep(int turn) {
        this.turn = turn;
    }

    @Override
    public GameLost execute(Game game) {
        game.startTurn(turn);

        return GameLost.FALSE;
    }
}
