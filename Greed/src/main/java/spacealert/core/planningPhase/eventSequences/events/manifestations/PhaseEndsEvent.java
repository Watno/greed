package spacealert.core.planningPhase.eventSequences.events.manifestations;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.Phase;
import spacealert.core.planningPhase.IPlanningPhaseExposedToEvents;
import spacealert.core.planningPhase.eventSequences.EventExecutor;
import spacealert.core.planningPhase.eventSequences.events.Notification;
import spacealert.core.planningPhase.eventSequences.events.NotificationType;
import spacealert.core.planningPhase.eventSequences.events.templates.AbstractEvent;

import java.time.Duration;

public class PhaseEndsEvent extends AbstractEvent {
    @JsonProperty
    private final Phase phase;

    public PhaseEndsEvent(long triggerTimeMinutes, long triggerTimeSeconds, Phase phase) {
        super(triggerTimeMinutes, triggerTimeSeconds);
        this.phase = phase;
    }

    @Override
    public void addToSchedule(EventExecutor eventExecutor, IPlanningPhaseExposedToEvents planningPhase) {
        eventExecutor.schedule(triggerTime.minus(Duration.ofMinutes(1)), () -> notifyPlayers(NotificationType.HAPPENSINAMINUTE, planningPhase));
        eventExecutor.schedule(triggerTime.minus(Duration.ofSeconds(20)), () -> notifyPlayers(NotificationType.HAPPENSINTWENTYSECONDS, planningPhase));
        super.addToSchedule(eventExecutor, planningPhase);
    }

    @Override
    protected void onTriggered(IPlanningPhaseExposedToEvents planningPhase) {
        planningPhase.endPhase(phase);
    }

    private void notifyPlayers(NotificationType notificationType, IPlanningPhaseExposedToEvents planningPhase) {
        planningPhase.notifyPlayers(new Notification(notificationType, this));
    }

}
