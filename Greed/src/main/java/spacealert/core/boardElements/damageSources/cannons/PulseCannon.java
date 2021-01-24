package spacealert.core.boardElements.damageSources.cannons;

import spacealert.core.BoardState;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.Threat;

import java.util.List;

public class PulseCannon extends Cannon {
    private boolean damaged = false;

    @Override
    public boolean usesEnergy() {
        return true;
    }

    @Override
    public void actuallyAssignDamage(BoardState boardState, List<Threat> threats) {
        List<Integer> targetedSectors = damaged ? List.of(1) : List.of(1, 2);
        var targets = getPossibleTargets(threats, targetedSectors, Zone.all);
        for (var target : targets) {
            target.assignDamageTo(boardState, 1, this);
        }
    }

    @Override
    public void damage() {
        damaged = true;
    }
}
