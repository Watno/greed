package spacealert.core.boardElements.locations;

import spacealert.core.Game;
import spacealert.core.ICrewMember;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;

public class UpperRedStation extends Location {

    UpperRedStation() {
        super(new Position(Deck.UPPER, Zone.RED));
    }

    @Override
    protected void executeBButton(Game game, ICrewMember crewMember) {
        game.getShield(Zone.RED).chargeFrom(game.getReactor(Zone.RED));
    }

    @Override
    protected void executeCButton(Game game, ICrewMember crewMember) {
        if (crewMember.hasActiveBattlebot()) {
            crewMember.moveTo(game.getSpace());
        }
    }
}
