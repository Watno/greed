package carnivalOfMonsters.core;

import carnivalOfMonsters.core.logging.ILogEntry;

import java.util.Optional;

public interface ICanBePlayed extends ICard {

    boolean checkRequirement(Player playingPlayer);

    void onPlay(Player playingPlayer, Game game, Optional<ILogEntry> loggingContext);
}
