package carnivalOfMonsters.core.logging;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HuntPhaseEntry extends LogEntry {
    final public List<Integer> dieRolls;

    public HuntPhaseEntry(Collection<Integer> rolls) {
        this.dieRolls = new ArrayList<>(rolls);
    }
}
