package spacealert.core.threats.implementations.external.nonserious.yellow;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.damageSources.Rocket;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class Jellyfish extends ExternalThreat {
    public Jellyfish(Zone zone) {
        super(2, 13, 3, 6, -2, zone);
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        heal(getDamageTaken() / 2);
        return attack(boardState, Zone.all, 1);
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        heal((getDamageTaken()) / 2);
        return attack(boardState, Zone.all, 1);
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return attack(boardState, Zone.all, 2);
    }

    @Override
    public boolean canBeTargetedBy(DamageSource damageSource) {
        return !(damageSource instanceof Rocket);
    }
}
