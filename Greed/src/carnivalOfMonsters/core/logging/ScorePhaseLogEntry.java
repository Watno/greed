package carnivalOfMonsters.core.logging;

public class ScorePhaseLogEntry extends LogEntry {
    public void addDependantEntry(PlayerScoreLogEntry playerScoreLogEntry) {
        addDependantEntry((LogEntry) playerScoreLogEntry);
    }
}
