package spacealert.core.threats.implementations.internal;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.ICrewMember;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Direction;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.Intruder;

public class CommandosBlue extends Intruder {

    public CommandosBlue() {
        super(2, 2, 4, 8, new Position(Deck.UPPER, Zone.BLUE));
    }

    @Override
    public boolean returnsFire() {
        return true;
    }

    @Override
    protected GameLost doXAction(Game game) {
        moveInDirection(game, Direction.GRAVOLIFT);
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(Game game) {
        if (currentHitPoints != maximumHitPoints) {
            moveInDirection(game, Direction.RED);
        } else {
            return damage(game, 2);
        }
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(Game game) {
        var gameLost = damage(game, 4);
        if (gameLost == GameLost.TRUE) return GameLost.TRUE;

        locations.stream().flatMap(x -> x.getCrewMembers().stream())
                .forEach(ICrewMember::becomeKnockedOut);
        return GameLost.FALSE;
    }
}
