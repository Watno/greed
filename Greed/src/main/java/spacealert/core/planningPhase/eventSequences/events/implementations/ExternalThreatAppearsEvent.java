package spacealert.core.planningPhase.eventSequences.events.implementations;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.planningPhase.eventSequences.events.AbstractEvent;
import spacealert.core.planningPhase.eventSequences.threatProviders.IThreatProvider;
import spacealert.core.threats.templates.ExternalThreat;

public class ExternalThreatAppearsEvent extends AbstractEvent {
    @JsonProperty
    private final int turn;

    @JsonProperty
    private final boolean seriousity;

    @JsonProperty
    private final Zone zone;

    @JsonProperty
    private final ExternalThreat threat;

    public ExternalThreatAppearsEvent(long triggerTimeMinutes, long triggerTimeSeconds, int turn, boolean seriousity, Zone zone, IThreatProvider threatProvider) {
        super(triggerTimeMinutes, triggerTimeSeconds);
        this.turn = turn;
        this.seriousity = seriousity;
        this.zone = zone;
        this.threat = threatProvider.provideExternalThreat(seriousity, zone);
    }

}
