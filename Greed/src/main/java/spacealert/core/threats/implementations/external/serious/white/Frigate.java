package spacealert.core.threats.implementations.external.serious.white;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class Frigate extends ExternalThreat {
    public Frigate(Zone zone) {
        super(2, 7, 4, 8, 2, zone);
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        return attack(boardState, 2);
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        return attack(boardState, 3);
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return attack(boardState, 4);
    }
}
