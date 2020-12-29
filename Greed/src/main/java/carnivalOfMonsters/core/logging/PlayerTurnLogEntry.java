package carnivalOfMonsters.core.logging;

public class PlayerTurnLogEntry extends LogEntry {
    public final String playername;

    public PlayerTurnLogEntry(String playername) {
        this.playername = playername;
    }
}
