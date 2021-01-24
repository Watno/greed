package spacealert.core.threats.implementations.external.nonserious.yellow;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.damageSources.Rocket;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class Swarm extends ExternalThreat {
    public Swarm(Zone zone) {
        super(4, 5, 3, 6, 2, zone);
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        return attack(boardState, 1);
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        return attack(boardState, 2)
                .then(attack(boardState, Zone.otherThan(zone), 1));
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return attack(boardState, 3)
                .then(attack(boardState, Zone.otherThan(zone), 2));
    }

    @Override
    public boolean canBeTargetedBy(DamageSource damageSource) {
        return !(damageSource instanceof Rocket);
    }

    @Override
    public GameLost resolveDamage(BoardState boardState) {
        assignedDamage = Integer.max(assignedDamage, 0);
        return super.resolveDamage(boardState);
    }
}
