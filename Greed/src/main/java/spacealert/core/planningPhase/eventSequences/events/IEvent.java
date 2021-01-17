package spacealert.core.planningPhase.eventSequences.events;

import spacealert.core.planningPhase.IPlanningPhaseExposedToEvents;
import spacealert.core.planningPhase.eventSequences.EventExecutor;

import java.time.Duration;

public interface IEvent {
    void addToSchedule(EventExecutor eventExecutor, IPlanningPhaseExposedToEvents planningPhase);

    Duration getTriggerTime();
}
