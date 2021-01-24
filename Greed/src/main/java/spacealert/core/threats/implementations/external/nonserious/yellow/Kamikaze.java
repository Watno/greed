package spacealert.core.threats.implementations.external.nonserious.yellow;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class Kamikaze extends ExternalThreat {
    public Kamikaze(Zone zone) {
        super(4, 5, 3, 6, 2, zone);
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        speed++;
        shieldPoints = 1;
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        speed++;
        shieldPoints = 0;
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return attack(boardState, 6);
    }
}
