package spacealert.core.planningPhase.eventSequences.events.implementations;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.planningPhase.eventSequences.events.AbstractEvent;
import spacealert.core.planningPhase.eventSequences.threatProviders.IThreatProvider;
import spacealert.core.threats.templates.InternalThreat;

public class InternalThreatAppearsEvent extends AbstractEvent {
    @JsonProperty
    private final int turn;

    @JsonProperty
    private final boolean seriousity;

    @JsonProperty
    private final InternalThreat threat;

    public InternalThreatAppearsEvent(long triggerTimeMinutes, long triggerTimeSeconds, int turn, boolean seriousity, IThreatProvider threatProvider) {
        super(triggerTimeMinutes, triggerTimeSeconds);
        this.turn = turn;
        this.seriousity = seriousity;
        this.threat = threatProvider.proviceInternalThreat(seriousity);
    }

}
