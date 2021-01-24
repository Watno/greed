package spacealert.core.threats.implementations.external.serious.yellow;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.damageSources.Rocket;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class Juggernaut extends ExternalThreat {
    public Juggernaut(Zone zone) {
        super(1, 10, 6, 12, 3, zone);
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        speed += 2;
        return attack(boardState, 2);
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        speed += 2;
        return attack(boardState, 3);
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return attack(boardState, 7);
    }

    @Override
    public boolean alwaysGetsTargetedBy(DamageSource damageSource) {
        return (damageSource instanceof Rocket);
    }

    @Override
    public void assignDamageTo(BoardState boardState, int damage, DamageSource source) {
        //damage +1 because shield reduction doesn't apply yet
        super.assignDamageTo(boardState, damage + 1, source);
        if (source instanceof Rocket) shieldPoints++;
    }
}
