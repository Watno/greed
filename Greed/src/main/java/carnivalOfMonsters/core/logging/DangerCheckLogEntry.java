package carnivalOfMonsters.core.logging;

public class DangerCheckLogEntry extends LogEntry {
    public final String playername;
    public final int dangerLevel;
    public final int spentHunterTokens;

    public DangerCheckLogEntry(String playername, int dangerLevel, int spentHunterTokens) {
        this.playername = playername;
        this.dangerLevel = dangerLevel;
        this.spentHunterTokens = spentHunterTokens;
    }
}
