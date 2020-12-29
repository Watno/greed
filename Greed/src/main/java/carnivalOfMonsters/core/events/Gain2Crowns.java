package carnivalOfMonsters.core.events;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.core.logging.ILogEntry;

import java.util.Optional;

public class Gain2Crowns extends Event {

    public Gain2Crowns(String name) {
        super(name);
    }

    @Override
    public void onPlay(Player playingPlayer, Game game, Optional<ILogEntry> loggingContext) {
        playingPlayer.gainCrowns(2);
    }

}
