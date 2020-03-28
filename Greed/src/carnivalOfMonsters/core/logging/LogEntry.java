package carnivalOfMonsters.core.logging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public abstract class LogEntry implements ILogEntry {
    @JsonProperty
    private List<ILogEntry> dependantEntries = new ArrayList<>();

    @Override
    public List<ILogEntry> getDependantEntries() {
        return dependantEntries;
    }

    @Override
    public void addDependantEntry(ILogEntry entry) {
        dependantEntries.add(entry);
    }
}
