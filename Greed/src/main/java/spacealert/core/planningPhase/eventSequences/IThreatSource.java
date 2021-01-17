package spacealert.core.planningPhase.eventSequences;

import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;
import spacealert.core.threats.templates.InternalThreat;

public interface IThreatSource {
    ExternalThreat provideExternalThreat(boolean serious, Zone zone);

    InternalThreat proviceInternalThreat(boolean serious);
}
