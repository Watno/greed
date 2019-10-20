package spacealert.core.tests;

import spacealert.core.Game;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.ExternalThreat;

public class TestThreat extends ExternalThreat {
    protected TestThreat() {
        super(2, 4, 5, 6, 1, Zone.WHITE);
    }

    @Override
    protected void doXAction(Game game) {

    }

    @Override
    protected void doYAction(Game game) {

    }

    @Override
    protected void doZAction(Game game) {

    }
}
