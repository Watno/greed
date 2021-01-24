package spacealert.core.planningPhase.eventSequences.threatProviders;

import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.implementations.external.nonserious.white.*;
import spacealert.core.threats.implementations.external.nonserious.yellow.*;
import spacealert.core.threats.implementations.external.serious.white.*;
import spacealert.core.threats.implementations.external.serious.yellow.*;
import spacealert.core.threats.implementations.internal.nonserious.white.*;
import spacealert.core.threats.implementations.internal.serious.white.*;
import spacealert.core.threats.templates.ExternalThreat;
import spacealert.core.threats.templates.InternalThreat;

import java.util.Collection;
import java.util.Collections;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Supplier;

public class RandomThreatProvider implements IThreatProvider {
    private final Stack<Function<Zone, ExternalThreat>> externalThreats;
    private final Stack<Function<Zone, ExternalThreat>> seriousExternalThreats;
    private final Stack<Supplier<InternalThreat>> internalThreats;
    private final Stack<Supplier<InternalThreat>> seriousInternalThreats;

    public RandomThreatProvider(Collection<ThreatLevel> levelsForNormalThreats, Collection<ThreatLevel> levelsForSeriousThreats) {
        externalThreats = new Stack<>();
        seriousExternalThreats = new Stack<>();
        internalThreats = new Stack<>();
        seriousInternalThreats = new Stack<>();

        if (levelsForNormalThreats.contains(ThreatLevel.WHITE)) {
            externalThreats.add(PulseBall::new);
            externalThreats.add(Destroyer::new);
            externalThreats.add(StealthFighter::new);
            externalThreats.add(EnergyCloud::new);
            externalThreats.add(Gunship::new);
            externalThreats.add(CryoshieldFighter::new);
            externalThreats.add(Fighter::new);
            externalThreats.add(ArmoredGrappler::new);
            externalThreats.add(Amoeba::new);
            externalThreats.add(Meteoroid::new);

            internalThreats.add(SkirmishersBlue::new);
            internalThreats.add(SkirmishersRed::new);
            internalThreats.add(SaboteurBlue::new);
            internalThreats.add(SaboteurRed::new);
            internalThreats.add(HackedShieldsBlue::new);
            internalThreats.add(HackedShieldsRed::new);
            internalThreats.add(UnstableWarheads::new);
        }

        if (levelsForNormalThreats.contains(ThreatLevel.YELLOW)) {
            externalThreats.add(Jellyfish::new);
            externalThreats.add(Kamikaze::new);
            externalThreats.add(MinorAsteroid::new);
            externalThreats.add(PhantomFighter::new);
            externalThreats.add(Swarm::new);

            //TODO internal, todos
        }

        if (levelsForSeriousThreats.contains(ThreatLevel.WHITE)) {
            seriousExternalThreats.add(ManOfWar::new);
            seriousExternalThreats.add(LeviathanTanker::new);
            seriousExternalThreats.add(PulseSatellite::new);
            seriousExternalThreats.add(CryoshieldFrigate::new);
            seriousExternalThreats.add(InterstellarOctopus::new);
            seriousExternalThreats.add(Maelstrom::new);
            seriousExternalThreats.add(Asteroid::new);

            seriousInternalThreats.add(CommandosBlue::new);
            seriousInternalThreats.add(CommandosRed::new);
            seriousInternalThreats.add(Alien::new);
            seriousInternalThreats.add(Fissure::new);
            seriousInternalThreats.add(CrossedWires::new);
            seriousInternalThreats.add(BattlebotUprising::new);
        }

        if (levelsForSeriousThreats.contains(ThreatLevel.YELLOW)) {
            seriousExternalThreats.add(Juggernaut::new);
            seriousExternalThreats.add(MajorAsteroid::new);
            seriousExternalThreats.add(NebulaCrab::new);
            seriousExternalThreats.add(Nemesis::new);
            seriousExternalThreats.add(PsionicSatellite::new);

            //TODO internal, todos
        }

        Collections.shuffle(externalThreats);
        Collections.shuffle(seriousExternalThreats);
        Collections.shuffle(internalThreats);
        Collections.shuffle(seriousInternalThreats);
    }

    @Override
    public Function<Zone, ExternalThreat> provideExternalThreat(boolean serious) {
        return serious ? seriousExternalThreats.pop() : externalThreats.pop();
    }

    @Override
    public Supplier<InternalThreat> provideInternalThreat(boolean serious) {
        return serious ? internalThreats.pop() : internalThreats.pop();
    }

}
