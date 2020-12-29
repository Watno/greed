package spacealert.core.tests;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class TestThreat extends ExternalThreat {
    protected TestThreat() {
        super(2, 4, 5, 6, 1, Zone.WHITE);
    }

    @Override
    protected GameLost doXAction(Game game) {
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(Game game) {
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(Game game) {
        return GameLost.FALSE;
    }
}
