package spacealert.core.boardElements.damageSources;

import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.Threat;

import java.util.List;

public class Interceptors extends DamageSource {
    @Override
    public void assignDamage(List<Threat> threats) {
        var targets = getPossibleTargets(threats, List.of(1), Zone.all);
        if (targets.size() == 1) {
            targets.get(0).assignDamageTo(3, this);
        }
        for (var target : targets) {
            target.assignDamageTo(1, this);
        }
    }
}
