package carnivalOfMonsters.core.logging;

public class SeasonLogEntry extends LogEntry {

    final public int seasonnumber;

    public SeasonLogEntry(int seasonnumber) {
        this.seasonnumber = seasonnumber;
    }

    public void addDependantEntry(HuntPhaseEntry huntPhaseEntry) {
        addDependantEntry((LogEntry) huntPhaseEntry);
    }
}
