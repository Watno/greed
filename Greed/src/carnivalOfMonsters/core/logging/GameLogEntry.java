package carnivalOfMonsters.core.logging;

public class GameLogEntry extends LogEntry {

    public void addDependantEntry(SeasonLogEntry seasonLogEntry) {
        addDependantEntry((LogEntry) seasonLogEntry);
    }

    public void addDependantEntry(ScorePhaseLogEntry scorePhaseLogEntry) {
        addDependantEntry((LogEntry) scorePhaseLogEntry);
    }
}
