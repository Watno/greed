package carnivalOfMonsters.core.logging;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public interface ILogEntry {
    List<ILogEntry> getDependantEntries();

    void addDependantEntry(ILogEntry entry);
}
