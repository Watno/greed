package carnivalOfMonsters.core.logging;

import carnivalOfMonsters.core.ITriggerOnPlay;

public class TriggerLogEntry extends LogEntry {
    public final ITriggerOnPlay triggeringCard;

    public TriggerLogEntry(ITriggerOnPlay triggeringCard) {
        this.triggeringCard = triggeringCard;
    }
}
