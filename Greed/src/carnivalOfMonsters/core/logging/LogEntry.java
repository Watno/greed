package carnivalOfMonsters.core.logging;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public abstract class LogEntry implements ILogEntry {
    @JsonProperty
    private List<ILogEntry> dependantEntries = new ArrayList<>();

    @Override
    public List<ILogEntry> getDependantEntries() {
        return dependantEntries;
    }

    @Override
    public void addDependantEntry(ILogEntry entry) {
        if (entry == null) {
            throw new IllegalArgumentException("Logentry is null");
        }
        dependantEntries.add(entry);
    }
}
