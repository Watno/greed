package spacealert.core.threats.implementations.todo;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class Scout extends ExternalThreat {
    public Scout(Zone zone) {
        super(2, 3, 3, 6, 1, zone);
    }

    //TODO

    @Override
    protected GameLost doXAction(BoardState boardState) {
        return null;
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        return null;
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return null;
    }
}