package spacealert.core.planningPhase;

import spacealert.core.Phase;
import spacealert.core.planningPhase.eventSequences.events.Notification;

public interface IPlanningPhaseExposedToEvents {
    void broadcastGameState();

    void notifyPlayers(Notification notification);

    void dealCards(int number);

    void allowCardPassing();

    void disallowCardPassing();

    void endPhase(Phase phase);
}
