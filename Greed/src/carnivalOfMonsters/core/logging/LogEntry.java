package carnivalOfMonsters.core.logging;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public abstract class LogEntry {
    @JsonProperty
    private List<LogEntry> dependantEntries = new ArrayList<>();

    public List<LogEntry> getDependantEntries() {
        return dependantEntries;
    }

    protected void addDependantEntry(LogEntry entry) {
        dependantEntries.add(entry);
    }
}
