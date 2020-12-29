package spacealert.core.threats.implementations.internal;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Direction;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.Intruder;

public class Alien extends Intruder {

    public Alien() {
        super(2, 2, 0, 8, new Position(Deck.LOWER, Zone.WHITE));
    }

    private boolean grownUp = false;

    @Override
    public boolean returnsFire() {
        return grownUp;
    }

    @Override
    protected GameLost doXAction(Game game) {
        grownUp = true;
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(Game game) {
        moveInDirection(game, Direction.GRAVOLIFT);
        return damage(game, locations.stream().mapToInt(x -> x.getCrewMembers().size()).sum());
    }

    @Override
    protected GameLost doZAction(Game game) {
        return GameLost.TRUE;
    }
}
