package spacealert.core.locations;

import spacealert.core.*;

public class UpperWhiteStation extends Location {
    private Position position = new Position(Deck.LOWER, Zone.WHITE);

    public UpperWhiteStation(Game game) {
        super(game);
    }

    @Override
    protected void executeAButton(ICrewMember crewMember) {

    }

    @Override
    protected void executeBButton(ICrewMember crewMember) {
        game.getShield(Zone.WHITE).chargeFrom(game.getReactor(Zone.WHITE));
    }

    @Override
    protected void executeCButton(ICrewMember crewMember) {

    }
}
