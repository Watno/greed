package carnivalOfMonsters.core.logging;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class HuntPhaseEntry extends LogEntry {
    @JsonProperty
    final public Collection<Integer> dieRolls;

    public HuntPhaseEntry(Collection rolls) {
        this.dieRolls = rolls;
    }
}
