package spacealert.core.boardElements.damageSources;

import spacealert.core.Game;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.Threat;

import java.util.List;

public class Interceptors extends DamageSource {
    @Override
    public void assignDamage(List<Threat> threats, Game game) {
        var targets = getPossibleTargets(threats, List.of(1), Zone.all);
        if (targets.size() == 1) {
            targets.get(0).assignDamageTo(game, 3, this);
        }
        for (var target : targets) {
            target.assignDamageTo(game, 1, this);
        }
    }
}
