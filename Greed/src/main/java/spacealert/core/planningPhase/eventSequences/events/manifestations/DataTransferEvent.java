package spacealert.core.planningPhase.eventSequences.events.manifestations;

import spacealert.core.planningPhase.IPlanningPhaseExposedToEvents;
import spacealert.core.planningPhase.eventSequences.events.templates.AbstractOngoingEvent;

public class DataTransferEvent extends AbstractOngoingEvent {
    public DataTransferEvent(long triggerTimeMinutes, long triggerTimeSeconds) {
        super(triggerTimeMinutes, triggerTimeSeconds, 10);
    }

    @Override
    protected void onTriggered(IPlanningPhaseExposedToEvents planningPhase) {
        planningPhase.allowCardPassing();
    }

    @Override
    protected void onEnded(IPlanningPhaseExposedToEvents planningPhase) {
        planningPhase.disallowCardPassing();
    }
}
