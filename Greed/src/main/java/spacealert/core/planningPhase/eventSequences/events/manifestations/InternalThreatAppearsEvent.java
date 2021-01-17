package spacealert.core.planningPhase.eventSequences.events.manifestations;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.planningPhase.eventSequences.IThreatSource;
import spacealert.core.planningPhase.eventSequences.events.templates.AbstractEvent;
import spacealert.core.threats.templates.InternalThreat;

public class InternalThreatAppearsEvent extends AbstractEvent {
    @JsonProperty
    private final int turn;

    @JsonProperty
    private final InternalThreat threat;

    public InternalThreatAppearsEvent(long triggerTimeMinutes, long triggerTimeSeconds, int turn, boolean seriousity, IThreatSource threatSource) {
        super(triggerTimeMinutes, triggerTimeSeconds);
        this.turn = turn;
        this.threat = threatSource.proviceInternalThreat(seriousity);
    }

}
