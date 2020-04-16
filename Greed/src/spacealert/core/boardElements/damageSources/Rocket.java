package spacealert.core.boardElements.damageSources;

import spacealert.core.Game;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.Threat;

import java.util.List;

public class Rocket extends DamageSource {
    @Override
    public void assignDamage(List<Threat> threats, Game game) {
        var targets = getPossibleTargets(threats, List.of(1, 2), Zone.all);
        if (!targets.isEmpty()) {
            targets.get(0).assignDamageTo(game, 3, this);
        }
    }
}
