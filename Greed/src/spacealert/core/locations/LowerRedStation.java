package spacealert.core.locations;

import spacealert.core.*;

public class LowerRedStation extends Location {
    private Position position = new Position(Deck.LOWER, Zone.RED);

    public LowerRedStation(Game game) {
        super(game);
    }

    @Override
    protected void executeAButton(ICrewMember crewMember) {

    }

    @Override
    protected void executeBButton(ICrewMember crewMember) {
        game.getReactor(Zone.RED).chargeFrom(game.getReactor(Zone.WHITE));
    }

    @Override
    protected void executeCButton(ICrewMember crewMember) {

    }
}
