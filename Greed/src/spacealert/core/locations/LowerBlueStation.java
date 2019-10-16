package spacealert.core.locations;

import spacealert.core.*;

public class LowerBlueStation extends Location {

    public LowerBlueStation() {
        super(new Position(Deck.LOWER, Zone.BLUE));
    }

    @Override
    protected void executeAButton(Game game, ICrewMember crewMember) {

    }

    @Override
    protected void executeBButton(Game game, ICrewMember crewMember) {
        game.getReactor(Zone.BLUE).chargeFrom(game.getReactor(Zone.WHITE));
    }

    @Override
    protected void executeCButton(Game game, ICrewMember crewMember) {

    }
}
