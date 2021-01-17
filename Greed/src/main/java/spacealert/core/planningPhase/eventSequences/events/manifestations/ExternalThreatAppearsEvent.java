package spacealert.core.planningPhase.eventSequences.events.manifestations;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.planningPhase.eventSequences.IThreatSource;
import spacealert.core.planningPhase.eventSequences.events.templates.AbstractEvent;
import spacealert.core.threats.templates.ExternalThreat;

public class ExternalThreatAppearsEvent extends AbstractEvent {
    @JsonProperty
    private final int turn;

    @JsonProperty
    private final ExternalThreat threat;

    public ExternalThreatAppearsEvent(long triggerTimeMinutes, long triggerTimeSeconds, int turn, boolean seriousity, Zone zone, IThreatSource threatSource) {
        super(triggerTimeMinutes, triggerTimeSeconds);
        this.turn = turn;
        this.threat = threatSource.provideExternalThreat(seriousity, zone);
    }

}
