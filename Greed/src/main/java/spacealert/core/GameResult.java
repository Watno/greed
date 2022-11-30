package spacealert.core;

public class GameResult {
    public boolean won;
    public BoardState finalBoardState;

    public GameResult(GameLost gameLost, BoardState finalBoardState) {
        this.won = gameLost == GameLost.FALSE;
        this.finalBoardState = finalBoardState;
    }
}
