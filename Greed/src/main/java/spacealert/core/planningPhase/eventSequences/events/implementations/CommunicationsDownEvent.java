package spacealert.core.planningPhase.eventSequences.events.implementations;

import spacealert.core.planningPhase.eventSequences.events.AbstractOngoingEvent;

public class CommunicationsDownEvent extends AbstractOngoingEvent {
    public CommunicationsDownEvent(long triggerTimeMinutes, long triggerTimeSeconds, long durationSeconds) {
        super(triggerTimeMinutes, triggerTimeSeconds, durationSeconds);
    }
}
