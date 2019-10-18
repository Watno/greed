package spacealert.core.missionSteps;

import spacealert.core.Game;

public class StartTurnStep implements IMissionStep {
    private int turn;

    StartTurnStep(int turn) {
        this.turn = turn;
    }

    @Override
    public void execute(Game game) {
        game.startTurn(turn);
    }
}
