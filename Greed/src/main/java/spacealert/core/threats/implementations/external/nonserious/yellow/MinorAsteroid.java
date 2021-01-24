package spacealert.core.threats.implementations.external.nonserious.yellow;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.damageSources.Rocket;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class MinorAsteroid extends ExternalThreat {
    public MinorAsteroid(Zone zone) {
        super(4, 7, 3, 6, 0, zone);
    }

    private int passedXAndYSquares = 0;

    @Override
    protected GameLost doXAction(BoardState boardState) {
        passedXAndYSquares++;
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        passedXAndYSquares++;
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return attack(boardState, currentHitPoints);
    }

    @Override
    public boolean canBeTargetedBy(DamageSource damageSource) {
        return !(damageSource instanceof Rocket);
    }

    @Override
    protected GameLost onDestroyed(BoardState boardState) {
        return attack(boardState, passedXAndYSquares);
    }
}
