package carnivalOfMonsters.core.events;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.core.logging.ILogEntry;

import java.util.Optional;

public class GainHunterToken extends Event {

    public GainHunterToken(String name) {
        super(name);
    }

    @Override
    public void onPlay(Player playingPlayer, Game game, Optional<ILogEntry> loggingContext) {
        playingPlayer.gainHunterToken();
    }

}
