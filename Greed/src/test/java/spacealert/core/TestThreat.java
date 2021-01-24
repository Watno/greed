package spacealert.core;

import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class TestThreat extends ExternalThreat {
    protected TestThreat() {
        super(2, 4, 5, 6, 1, Zone.WHITE);
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return GameLost.FALSE;
    }
}
