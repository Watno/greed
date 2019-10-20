package spacealert.core.boardElements.damageSources.cannons;

import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.ExternalThreat;

import java.util.List;

public abstract class HeavyOrLightCannon extends Cannon {
    private Zone zone;
    private int damage;

    HeavyOrLightCannon(Zone zone, int damage) {
        this.zone = zone;
        this.damage = damage;
    }

    @Override
    public void actuallyAssignDamage(List<ExternalThreat> externalThreats) {
        var targets = getPossibleTargets(externalThreats, List.of(1, 2, 3), List.of(zone));
        if (!targets.isEmpty()) {
            targets.get(0).assignDamageTo(damage, this);
        }
    }
}
