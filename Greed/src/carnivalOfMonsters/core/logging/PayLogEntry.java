package carnivalOfMonsters.core.logging;

public class PayLogEntry extends LogEntry {
    public final String playername;
    public final int amount;
    public final int loans;

    public PayLogEntry(String playername, int amount, int loans) {
        this.playername = playername;
        this.amount = amount;
        this.loans = loans;
    }
}
