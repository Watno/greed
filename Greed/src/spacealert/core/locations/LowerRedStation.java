package spacealert.core.locations;

import spacealert.core.*;

public class LowerRedStation extends Location {

    public LowerRedStation() {
        super(new Position(Deck.LOWER, Zone.RED));
    }

    @Override
    protected void executeAButton(Game game, ICrewMember crewMember) {

    }

    @Override
    protected void executeBButton(Game game, ICrewMember crewMember) {
        game.getReactor(Zone.RED).chargeFrom(game.getReactor(Zone.WHITE));
    }

    @Override
    protected void executeCButton(Game game, ICrewMember crewMember) {

    }
}
