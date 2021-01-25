package spacealert.core.gamestates;

import java.util.Collection;

public class PublicGameState {
    final public Collection<PublicPlayerGameState> playerGameStates;
    final public Collection<PublicAndroidGameState> androids;

    public PublicGameState(Collection<PublicPlayerGameState> playerGameStates, Collection<PublicAndroidGameState> androids) {
        this.playerGameStates = playerGameStates;
        this.androids = androids;
    }
}
