package spacealert.core.planningPhase.eventSequences.events.manifestations;

import spacealert.core.planningPhase.eventSequences.events.templates.AbstractOngoingEvent;

public class CommunicationsDownEvent extends AbstractOngoingEvent {
    public CommunicationsDownEvent(long triggerTimeMinutes, long triggerTimeSeconds, long durationSeconds) {
        super(triggerTimeMinutes, triggerTimeSeconds, durationSeconds);
    }
}
