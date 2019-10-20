package spacealert.core.boardElements.damageSources;

import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.ExternalThreat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DamageSource {

    public abstract void assignDamage(List<ExternalThreat> externalThreats);

    protected List<ExternalThreat> getPossibleTargets(List<ExternalThreat> externalThreats, List<Integer> ranges, List<Zone> zones) {

        var alwaysTargetedThreats = externalThreats.stream()
                .filter(x -> x.alwaysGetsTargetedBy(this))
                .collect(Collectors.toList());
        if (!alwaysTargetedThreats.isEmpty()) return alwaysTargetedThreats;

        return externalThreats.stream()
                .filter(x -> x.canBeTargetedBy(this))
                .filter(x -> x.isIn(zones))
                .filter(x -> ranges.contains(x.getDistance()))
                .sorted(Comparator.comparing(ExternalThreat::getSquareOnTrajectory))
                .collect(Collectors.toList());
    }
}
