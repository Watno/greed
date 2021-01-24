package spacealert.core.missionSteps;

import spacealert.core.BoardState;
import spacealert.core.GameLost;

public class StartTurnStep implements IMissionStep {
    private final int turn;

    StartTurnStep(int turn) {
        this.turn = turn;
    }

    @Override
    public GameLost execute(BoardState boardState) {
        boardState.startTurn(turn);

        return GameLost.FALSE;
    }
}
