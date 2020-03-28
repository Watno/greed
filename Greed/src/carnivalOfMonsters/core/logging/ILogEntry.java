package carnivalOfMonsters.core.logging;

import java.util.List;

public interface ILogEntry {
    List<ILogEntry> getDependantEntries();

    void addDependantEntry(ILogEntry entry);
}
