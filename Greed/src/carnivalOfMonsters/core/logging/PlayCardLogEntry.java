package carnivalOfMonsters.core.logging;

import carnivalOfMonsters.core.ICanBePlayed;

public class PlayCardLogEntry extends LogEntry {
    public final String playername;
    public final ICanBePlayed card;
    public final PlaySource playSource;

    public PlayCardLogEntry(String playername, ICanBePlayed card, PlaySource playSource) {
        this.playername = playername;
        this.card = card;
        this.playSource = playSource;
    }
}
