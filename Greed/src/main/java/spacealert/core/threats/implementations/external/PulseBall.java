package spacealert.core.threats.implementations.external;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class PulseBall extends ExternalThreat {
    public PulseBall(Zone zone) {
        super(2, 5, 2, 4, 1, zone);
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        return attack(boardState, Zone.all, 1);
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        return attack(boardState, Zone.all, 1);
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return attack(boardState, Zone.all, 2);
    }
}
