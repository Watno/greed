package carnivalOfMonsters.core.gamestate;

import carnivalOfMonsters.core.seasons.Season;

import java.util.Collection;

public class PublicGameState {
    public Season currentSeason;
    public Collection<PublicPlayerGameState> playerGameStates;

    public PublicGameState(Season currentSeason, Collection<PublicPlayerGameState> playerGameStates) {
        this.currentSeason = currentSeason;
        this.playerGameStates = playerGameStates;
    }
}
