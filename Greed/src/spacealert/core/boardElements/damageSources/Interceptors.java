package spacealert.core.boardElements.damageSources;

import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

import java.util.List;

public class Interceptors extends DamageSource {
    @Override
    public void assignDamage(List<ExternalThreat> externalThreats) {
        var targets = getPossibleTargets(externalThreats, List.of(1), Zone.all);
        if (targets.size() == 1) {
            targets.get(0).assignDamageTo(3, this);
        }
        for (var target : targets) {
            target.assignDamageTo(1, this);
        }
    }
}
