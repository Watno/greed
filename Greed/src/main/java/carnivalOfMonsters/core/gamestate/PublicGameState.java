package carnivalOfMonsters.core.gamestate;

import carnivalOfMonsters.core.logging.GameLogEntry;
import carnivalOfMonsters.core.seasons.Season;

import java.util.Collection;

public class PublicGameState {
    final public Season currentSeason;
    final public Collection<PublicPlayerGameState> playerGameStates;
    final public GameLogEntry gameLog;

    public PublicGameState(Season currentSeason, Collection<PublicPlayerGameState> playerGameStates, GameLogEntry gameLog) {
        this.currentSeason = currentSeason;
        this.playerGameStates = playerGameStates;
        this.gameLog = gameLog;
    }
}
