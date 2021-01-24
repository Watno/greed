package spacealert.core.planningPhase.eventSequences;

import spacealert.core.missionSteps.DefaultMissionStepSequence;
import spacealert.core.missionSteps.MissionStepSequence;
import spacealert.core.planningPhase.eventSequences.events.IEvent;
import spacealert.core.threats.templates.Threat;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class EventSequence {
    private final ArrayList<IEvent> events;

    protected EventSequence(Collection<IEvent> events) {this.events = new ArrayList<>(events);}

    public ArrayList<IEvent> getEvents() {
        return events;
    }

    public Duration getLength() {
        return events.stream().map(IEvent::getTriggerTime).max(Duration::compareTo).orElse(Duration.ZERO);
    }

    public MissionStepSequence toMissionStepSequence() {
        var threatsBySpawn = Stream.<List<Threat>>generate(ArrayList::new).limit(8).collect(Collectors.toList());
        for (var event : events) {
            event.contributeToThreatsBySpawn(threatsBySpawn);
        }
        return new DefaultMissionStepSequence(threatsBySpawn);
    }

}
