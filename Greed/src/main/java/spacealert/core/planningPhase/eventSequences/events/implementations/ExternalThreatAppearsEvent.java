package spacealert.core.planningPhase.eventSequences.events.implementations;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.planningPhase.IPlanningPhaseExposedToEvents;
import spacealert.core.planningPhase.eventSequences.events.AbstractEvent;
import spacealert.core.planningPhase.eventSequences.threatProviders.IThreatProvider;
import spacealert.core.threats.templates.ExternalThreat;
import spacealert.core.threats.templates.Threat;

import java.util.List;
import java.util.function.Supplier;

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
        Supplier<ExternalThreat> threatSupplier = (() -> threatProvider.provideExternalThreat(seriousity).apply(zone));
        this.threat = threatSupplier.get();
    }

    @Override
    public void contributeToThreatsBySpawn(List<List<Threat>> threatsBySpawn) {
        threatsBySpawn.get(turn - 1).add(threat);
    }

    @Override
    protected void onTriggered(IPlanningPhaseExposedToEvents planningPhase) {
        planningPhase.addThreat(turn, threat);
    }
}
