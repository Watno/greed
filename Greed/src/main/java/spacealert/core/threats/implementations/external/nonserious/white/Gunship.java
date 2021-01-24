package spacealert.core.threats.implementations.external.nonserious.white;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class Gunship extends ExternalThreat {
    public Gunship(Zone zone) {
        super(2, 5, 2, 4, 2, zone);
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        return attack(boardState, 2);
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        return attack(boardState, 2);
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return attack(boardState, 3);
    }
}
