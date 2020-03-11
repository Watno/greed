package carnivalOfMonsters.core.gamestate;

public class GameStateWithPrivateInfo {
    public PublicGameState publicGameState;
    public PrivateGameState privateGameState;

    public GameStateWithPrivateInfo(PublicGameState publicGameState, PrivateGameState privateGameState) {
        this.publicGameState = publicGameState;
        this.privateGameState = privateGameState;
    }
}
