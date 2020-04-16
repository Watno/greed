package spacealert.core.threats.implementations.internal;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Direction;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.Intruder;

public class SkirmishersRed extends Intruder {

    public SkirmishersRed() {
        super(3, 1, 2, 4, new Position(Deck.UPPER, Zone.RED));
    }

    @Override
    public boolean returnsFire() {
        return true;
    }

    @Override
    protected GameLost doXAction(Game game) {
        moveInDirection(game, Direction.BLUE);
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(Game game) {
        moveInDirection(game, Direction.GRAVOLIFT);
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(Game game) {
        return damage(game, 3);
    }
}
