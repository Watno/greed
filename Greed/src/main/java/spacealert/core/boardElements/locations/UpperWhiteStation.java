package spacealert.core.boardElements.locations;

import spacealert.core.Game;
import spacealert.core.ICrewMember;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;

public class UpperWhiteStation extends Location {

    UpperWhiteStation() {
        super(new Position(Deck.UPPER, Zone.WHITE));
    }

    @Override
    protected void executeBButton(Game game, ICrewMember crewMember) {
        game.getShield(Zone.WHITE).chargeFrom(game.getReactor(Zone.WHITE));
    }

    @Override
    protected void executeCButton(Game game, ICrewMember crewMember) {
        game.juggleMouse();
    }
}
