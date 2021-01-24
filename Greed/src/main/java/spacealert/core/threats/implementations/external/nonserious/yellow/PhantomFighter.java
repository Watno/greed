package spacealert.core.threats.implementations.external.nonserious.yellow;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.damageSources.Rocket;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class PhantomFighter extends ExternalThreat {
    public PhantomFighter(Zone zone) {
        super(3, 3, 3, 6, 3, zone);
    }

    private boolean revealed;

    @Override
    public boolean canBeTargetedBy(DamageSource damageSource) {
        return revealed;
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        revealed = true;
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        return attack(boardState, 2);
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return attack(boardState, 3);
    }

    @Override
    public void assignDamageTo(BoardState boardState, int damage, DamageSource source) {
        if (!(source instanceof Rocket)) {
            super.assignDamageTo(boardState, damage, source);
        }
    }
}
