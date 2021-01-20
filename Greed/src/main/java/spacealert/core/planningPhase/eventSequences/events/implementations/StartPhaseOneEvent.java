package spacealert.core.planningPhase.eventSequences.events.implementations;

import spacealert.core.planningPhase.IPlanningPhaseExposedToEvents;
import spacealert.core.planningPhase.eventSequences.events.AbstractEvent;

public class StartPhaseOneEvent extends AbstractEvent {
    public StartPhaseOneEvent() {
        super(0, 0);
    }

    @Override
    protected void onTriggered(IPlanningPhaseExposedToEvents planningPhase) {
        planningPhase.dealCards(5);
    }
}
