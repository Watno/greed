package carnivalOfMonsters.core;

import carnivalOfMonsters.core.logging.ILogEntry;

import java.util.Optional;

public interface ICanBeScored {
    int score(Player player, Optional<ILogEntry> loggingContext);
}
