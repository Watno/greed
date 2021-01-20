package spacealert.core.planningPhase.eventSequences.threatProviders;

import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.implementations.external.Amoeba;
import spacealert.core.threats.implementations.internal.Alien;
import spacealert.core.threats.templates.ExternalThreat;
import spacealert.core.threats.templates.InternalThreat;

public class RandomThreatProvider implements IThreatProvider {

    @Override
    public ExternalThreat provideExternalThreat(boolean serious, Zone zone) {
        //TODO
        return new Amoeba(zone);
    }

    @Override
    public InternalThreat proviceInternalThreat(boolean serious) {
        //TODO
        return new Alien();
    }
}
