package spacealert.core.locations;

import spacealert.core.*;

public class UpperWhiteStation extends Location {

    public UpperWhiteStation() {
        super(new Position(Deck.LOWER, Zone.WHITE));
    }

    @Override
    protected void executeAButton(Game game, ICrewMember crewMember) {

    }

    @Override
    protected void executeBButton(Game game, ICrewMember crewMember) {
        game.getShield(Zone.WHITE).chargeFrom(game.getReactor(Zone.WHITE));
    }

    @Override
    protected void executeCButton(Game game, ICrewMember crewMember) {

    }
}
