package carnivalOfMonsters.core.gamestate;

public class GameStateWithPrivateInfo {
    final public PublicGameState publicGameState;
    final public PrivateGameState privateGameState;

    public GameStateWithPrivateInfo(PublicGameState publicGameState, PrivateGameState privateGameState) {
        this.publicGameState = publicGameState;
        this.privateGameState = privateGameState;
    }
}
