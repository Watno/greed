package spacealert.core.planningPhase.eventSequences.threatProviders;

import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;
import spacealert.core.threats.templates.InternalThreat;

import java.util.function.Function;
import java.util.function.Supplier;

public interface IThreatProvider {

    Function<Zone, ExternalThreat> provideExternalThreat(boolean serious);

    Supplier<InternalThreat> provideInternalThreat(boolean serious);
}
