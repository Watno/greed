package spacealert.core.threats.implementations.external;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class PulseSatellite extends ExternalThreat {
    public PulseSatellite(Zone zone) {
        super(3, 4, 4, 8, 2, zone);
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        return attack(boardState, Zone.all, 1);
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        return attack(boardState, Zone.all, 2);
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return attack(boardState, Zone.all, 3);
    }

    @Override
    public boolean canBeTargetedBy(DamageSource damageSource) {
        return getDistance() != 3;
    }
}
