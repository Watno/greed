package spacealert.core.locations;

import spacealert.core.*;

public class LowerBlueStation extends Location {
    private Position position = new Position(Deck.LOWER, Zone.BLUE);

    public LowerBlueStation(Game game) {
        super(game);
    }

    @Override
    protected void executeAButton(ICrewMember crewMember) {

    }

    @Override
    protected void executeBButton(ICrewMember crewMember) {
        game.getReactor(Zone.BLUE).chargeFrom(game.getReactor(Zone.WHITE));
    }

    @Override
    protected void executeCButton(ICrewMember crewMember) {

    }
}
