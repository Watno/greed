package spacealert.core.planningPhase.eventSequences.events.templates;

import spacealert.core.planningPhase.IPlanningPhaseExposedToEvents;
import spacealert.core.planningPhase.eventSequences.EventExecutor;
import spacealert.core.planningPhase.eventSequences.events.Notification;
import spacealert.core.planningPhase.eventSequences.events.NotificationType;

import java.time.Duration;

public abstract class AbstractOngoingEvent extends AbstractEvent {
    private final Duration duration;

    protected AbstractOngoingEvent(long triggerTimeMinutes, long triggerTimeSeconds, long durationSeconds) {
        super(triggerTimeMinutes, triggerTimeSeconds);
        this.duration = Duration.ofSeconds(durationSeconds);
    }

    @Override
    public void addToSchedule(EventExecutor eventExecutor, IPlanningPhaseExposedToEvents planningPhase) {
        super.addToSchedule(eventExecutor, planningPhase);
        eventExecutor.schedule(triggerTime.plus(duration), () -> end(planningPhase));

    }

    private void end(IPlanningPhaseExposedToEvents planningPhase) {
        planningPhase.notifyPlayers(new Notification(NotificationType.ENDED, this));
        onEnded(planningPhase);
    }

    protected void onEnded(IPlanningPhaseExposedToEvents planningPhase) {
    }

}
