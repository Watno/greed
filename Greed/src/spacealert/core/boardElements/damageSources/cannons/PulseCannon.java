package spacealert.core.boardElements.damageSources.cannons;

import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.ExternalThreat;

import java.util.List;

public class PulseCannon extends Cannon {
    @Override
    public boolean usesEnergy() {
        return true;
    }

    @Override
    public void actuallyAssignDamage(List<ExternalThreat> externalThreats) {
        var targets = getPossibleTargets(externalThreats, List.of(1, 2), Zone.all);
        for (var target : targets) {
            target.assignDamageTo(1, this);
        }
    }
}
