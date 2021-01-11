package spacealert.core.gamestates;

import spacealert.core.planningPhase.Android;

import java.util.Collection;

public class PublicGameState {
    final public Collection<PublicPlayerGameState> playerGameStates;
    final public Collection<Android> androids;

    public PublicGameState(Collection<PublicPlayerGameState> playerGameStates, Collection<Android> androids) {
        this.playerGameStates = playerGameStates;
        this.androids = androids;
    }
}
