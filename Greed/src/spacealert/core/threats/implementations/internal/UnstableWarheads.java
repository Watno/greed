package spacealert.core.threats.implementations.internal;

import spacealert.core.Button;
import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.Malfunction;

public class UnstableWarheads extends Malfunction {

    public UnstableWarheads() {
        super(3, 3, 2, 4, new Position(Deck.LOWER, Zone.BLUE), Button.C);
    }

    @Override
    protected void onSpawn(Game game) {
        currentHitPoints = game.getAvailableRockets().size();
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
        return damage(game, 3 * currentHitPoints);
    }
}
