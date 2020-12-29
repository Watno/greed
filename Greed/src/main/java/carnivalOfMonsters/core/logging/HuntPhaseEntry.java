package carnivalOfMonsters.core.logging;

import java.util.Collection;

public class HuntPhaseEntry extends LogEntry {
    final public Collection<Integer> dieRolls;

    public HuntPhaseEntry(Collection rolls) {
        this.dieRolls = rolls;
    }
}
