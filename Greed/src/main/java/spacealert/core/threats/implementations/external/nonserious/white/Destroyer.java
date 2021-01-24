package spacealert.core.threats.implementations.external.nonserious.white;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class Destroyer extends ExternalThreat {
    public Destroyer(Zone zone) {
        super(2, 5, 2, 4, 2, zone);
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        return attackWithDoubleDamageAfterShields(boardState, 1);
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        return attackWithDoubleDamageAfterShields(boardState, 2);
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return attackWithDoubleDamageAfterShields(boardState, 2);
    }

    private GameLost attackWithDoubleDamageAfterShields(BoardState boardState, int amount) {
        var remainingDamage = boardState.applyShieldsToDamage(this.zone, amount);
        return boardState.damage(zone, remainingDamage * 2);
    }
}
