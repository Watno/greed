package spacealert.core.planningPhase.eventSequences.events.implementations;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.planningPhase.eventSequences.events.AbstractEvent;
import spacealert.core.planningPhase.eventSequences.threatProviders.IThreatProvider;
import spacealert.core.threats.templates.InternalThreat;
import spacealert.core.threats.templates.Threat;

import java.util.List;
import java.util.function.Supplier;

public class InternalThreatAppearsEvent extends AbstractEvent {
    @JsonProperty
    private final int turn;
    @JsonProperty
    private final boolean seriousity;
    @JsonProperty
    private final InternalThreat threat;

    private final Supplier<InternalThreat> threatSupplier;

    public InternalThreatAppearsEvent(long triggerTimeMinutes, long triggerTimeSeconds, int turn, boolean seriousity, IThreatProvider threatProvider) {
        super(triggerTimeMinutes, triggerTimeSeconds);
        this.turn = turn;
        this.seriousity = seriousity;
        this.threatSupplier = threatProvider.provideInternalThreat(seriousity);
        this.threat = threatSupplier.get();
    }

    @Override
    public void contributeToThreatsBySpawn(List<List<Threat>> threatsBySpawn) {
        threatsBySpawn.get(turn - 1).add(threatSupplier.get());
    }

}
