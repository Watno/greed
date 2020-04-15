package spacealert.core.boardElements.damageSources;

import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

import java.util.List;

public class Rocket extends DamageSource {
    @Override
    public void assignDamage(List<ExternalThreat> externalThreats) {
        var targets = getPossibleTargets(externalThreats, List.of(1, 2), Zone.all);
        if (!targets.isEmpty()) {
            targets.get(0).assignDamageTo(3, this);
        }
    }
}
