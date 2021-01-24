package spacealert.core.threats.implementations.external.serious.white;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class ManOfWar extends ExternalThreat {
    public ManOfWar(Zone zone) {
        super(1, 9, 4, 8, 2, zone);
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        speed++;
        return attack(boardState, 2);
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        shieldPoints++;
        return attack(boardState, 3);
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return attack(boardState, 5);
    }
}
