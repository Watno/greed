package spacealert.core.boardElements.damageSources;

import spacealert.core.Game;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;
import spacealert.core.threats.templates.Threat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DamageSource {

    public abstract void assignDamage(List<Threat> externalThreats, Game game);

    protected List<? extends Threat> getPossibleTargets(List<Threat> threats, List<Integer> ranges, List<Zone> zones) {

        var alwaysTargetedThreats = threats.stream()
                .filter(x -> x.alwaysGetsTargetedBy(this))
                .collect(Collectors.toList());
        if (!alwaysTargetedThreats.isEmpty()) return alwaysTargetedThreats;

        return threats.stream()
                .filter(x -> x instanceof ExternalThreat)
                .map(x -> (ExternalThreat) x)
                .filter(x -> x.canBeTargetedBy(this))
                .filter(x -> x.isIn(zones))
                .filter(x -> ranges.contains(x.getDistance()))
                .sorted(Comparator
                        .comparing(ExternalThreat::getSquareOnTrajectory)
                        .thenComparing(ExternalThreat::getSpawnTurn))
                .collect(Collectors.toList());
    }
}
