package spacealert.core.planningPhase.eventSequences.threatProviders;

import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;
import spacealert.core.threats.templates.InternalThreat;

public interface IThreatProvider {
    ExternalThreat provideExternalThreat(boolean serious, Zone zone);

    InternalThreat proviceInternalThreat(boolean serious);
}
