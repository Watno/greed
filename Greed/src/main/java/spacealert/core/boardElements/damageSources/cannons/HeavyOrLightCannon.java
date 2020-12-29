package spacealert.core.boardElements.damageSources.cannons;

import spacealert.core.Game;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.Threat;

import java.util.List;

public abstract class HeavyOrLightCannon extends Cannon {
    private Zone zone;
    private int damage;

    HeavyOrLightCannon(Zone zone, int damage) {
        this.zone = zone;
        this.damage = damage;
    }

    @Override
    public void actuallyAssignDamage(Game game, List<Threat> threats) {
        var targets = getPossibleTargets(threats, List.of(1, 2, 3), List.of(zone));
        if (!targets.isEmpty()) {
            targets.get(0).assignDamageTo(game, damage, this);
        }
    }

    @Override
    public void damage() {
        damage--;
    }
}
