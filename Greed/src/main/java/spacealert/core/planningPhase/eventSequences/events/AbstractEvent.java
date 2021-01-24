package spacealert.core.planningPhase.eventSequences.events;

import spacealert.core.planningPhase.IPlanningPhaseExposedToEvents;
import spacealert.core.planningPhase.eventSequences.EventExecutor;
import spacealert.core.threats.templates.Threat;

import java.time.Duration;
import java.util.List;

public abstract class AbstractEvent implements IEvent {
    protected Duration triggerTime;

    protected AbstractEvent(long triggerTimeMinutes, long triggerTimeSeconds) {
        this.triggerTime = Duration.ofMinutes(triggerTimeMinutes).plusSeconds(triggerTimeSeconds);
    }

    @Override
    public void addToSchedule(EventExecutor eventExecutor, IPlanningPhaseExposedToEvents planningPhase) {
        eventExecutor.schedule(triggerTime, () -> trigger(planningPhase));
    }

    private void trigger(IPlanningPhaseExposedToEvents planningPhase) {
        planningPhase.notifyPlayers(new Notification(NotificationType.TRIGGERED, this));
        onTriggered(planningPhase);
        planningPhase.broadcastGameState();
    }

    protected void onTriggered(IPlanningPhaseExposedToEvents planningPhase) { }

    @Override
    public Duration getTriggerTime() {
        return triggerTime;
    }

    @Override
    public void contributeToThreatsBySpawn(List<List<Threat>> threatsBySpawn) { }

}
