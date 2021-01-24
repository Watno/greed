package spacealert.core.threats.implementations.external;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class ArmoredGrappler extends ExternalThreat {
    public ArmoredGrappler(Zone zone) {
        super(2, 4, 2, 4, 3, zone);
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        return attack(boardState, 1);
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        heal(1);
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return attack(boardState, 4);
    }
}
