package spacealert.core.threats.implementations.external.serious.yellow;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.damageSources.Rocket;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

import java.util.List;

public class NebulaCrab extends ExternalThreat {
    public NebulaCrab(Zone zone) {
        super(2, 7, 6, 12, 2, zone);
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        shieldPoints = 4;
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        speed += 2;
        shieldPoints = 2;
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return attack(boardState, List.of(Zone.BLUE, Zone.RED), 5);
    }

    @Override
    public boolean canBeTargetedBy(DamageSource damageSource) {
        return !(damageSource instanceof Rocket);
    }
}
