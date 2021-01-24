package spacealert.core.planningPhase.eventSequences.events;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import spacealert.core.planningPhase.IPlanningPhaseExposedToEvents;
import spacealert.core.planningPhase.eventSequences.EventExecutor;
import spacealert.core.threats.templates.Threat;

import java.time.Duration;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public interface IEvent {
    void addToSchedule(EventExecutor eventExecutor, IPlanningPhaseExposedToEvents planningPhase);

    Duration getTriggerTime();

    void contributeToThreatsBySpawn(List<List<Threat>> threatsBySpawn);
}
