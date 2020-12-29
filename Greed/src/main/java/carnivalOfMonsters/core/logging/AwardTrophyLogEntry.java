package carnivalOfMonsters.core.logging;

import carnivalOfMonsters.core.seasons.Season;

import java.util.Optional;

public class AwardTrophyLogEntry extends LogEntry {
    public final Optional<String> playername;
    public final Season trophy;

    public AwardTrophyLogEntry(Optional<String> playername, Season trophy) {
        this.playername = playername;
        this.trophy = trophy;
    }
}
