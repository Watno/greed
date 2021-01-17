package spacealert.core.planningPhase.eventSequences;

import spacealert.core.planningPhase.eventSequences.events.IEvent;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;

public abstract class EventSequence {
    private final ArrayList<IEvent> events;

    protected EventSequence(Collection<IEvent> events) {this.events = new ArrayList<>(events);}

    public ArrayList<IEvent> getEvents() {
        return events;
    }

    public Duration getLength() {
        return events.stream().map(IEvent::getTriggerTime).max(Duration::compareTo).orElse(Duration.ZERO);
    }
}
