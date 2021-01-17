package spacealert.core.planningPhase.eventSequences.events;

public class Notification {
    public final NotificationType notificationType;
    public final IEvent event;

    public Notification(NotificationType notificationType, IEvent event) {
        this.notificationType = notificationType;
        this.event = event;
    }
}
