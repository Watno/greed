package spacealert.core.locations;

import spacealert.core.*;

public class UpperRedStation extends Location {
    private Position position = new Position(Deck.LOWER, Zone.RED);

    public UpperRedStation(Game game) {
        super(game);
    }

    @Override
    protected void executeAButton(ICrewMember crewMember) {

    }

    @Override
    protected void executeBButton(ICrewMember crewMember) {
        game.getShield(Zone.RED).chargeFrom(game.getReactor(Zone.RED));
    }

    @Override
    protected void executeCButton(ICrewMember crewMember) {

    }
}
