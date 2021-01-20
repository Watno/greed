package spacealert.core.planningPhase.eventSequences.events.implementations;

import spacealert.core.planningPhase.IPlanningPhaseExposedToEvents;
import spacealert.core.planningPhase.eventSequences.events.AbstractEvent;

public class IncomingDataEvent extends AbstractEvent {
    public IncomingDataEvent(long triggerTimeMinutes, long triggerTimeSeconds) {
        super(triggerTimeMinutes, triggerTimeSeconds);
    }

    @Override
    protected void onTriggered(IPlanningPhaseExposedToEvents planningPhase) {
        planningPhase.dealCards(1);
    }
}
