package carnivalOfMonsters.core.logging;

public class TurnLogEntry extends LogEntry {
    public final int turnNumber;

    public TurnLogEntry(int turnNumber) {
        this.turnNumber = turnNumber;
    }
}
