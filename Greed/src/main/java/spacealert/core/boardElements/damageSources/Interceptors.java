package spacealert.core.boardElements.damageSources;

import spacealert.core.BoardState;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.Threat;

import java.util.List;

public class Interceptors extends DamageSource {
    @Override
    public void assignDamage(List<Threat> threats, BoardState boardState) {
        var targets = getPossibleTargets(threats, List.of(1), Zone.all);
        if (targets.size() == 1) {
            targets.get(0).assignDamageTo(boardState, 3, this);
        }
        for (var target : targets) {
            target.assignDamageTo(boardState, 1, this);
        }
    }
}
