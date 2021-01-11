package spacealert.core.boardElements.locations;

import spacealert.core.Game;
import spacealert.core.ICrewMember;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;

public class LowerBlueStation extends Location {
    LowerBlueStation() {
        super(new Position(Deck.LOWER, Zone.BLUE));
    }

    @Override
    protected void executeBButton(Game game, ICrewMember crewMember) {
        game.getReactor(Zone.BLUE).chargeFrom(game.getReactor(Zone.WHITE));
    }

    @Override
    protected void executeCButton(Game game, ICrewMember crewMember) {
        game.tryLaunchRocket();
    }
}
