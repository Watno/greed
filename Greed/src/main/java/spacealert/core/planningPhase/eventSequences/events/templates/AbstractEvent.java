package spacealert.core.planningPhase.eventSequences.events.templates;

import spacealert.core.planningPhase.IPlanningPhaseExposedToEvents;
import spacealert.core.planningPhase.eventSequences.EventExecutor;
import spacealert.core.planningPhase.eventSequences.events.IEvent;
import spacealert.core.planningPhase.eventSequences.events.Notification;
import spacealert.core.planningPhase.eventSequences.events.NotificationType;

import java.time.Duration;

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
    }

    protected void onTriggered(IPlanningPhaseExposedToEvents planningPhase) {
    }

    @Override
    public Duration getTriggerTime() {
        return triggerTime;
    }

}
