package spacealert.core.locations;

import spacealert.core.*;

public class UpperBlueStation extends Location {
    private Position position = new Position(Deck.UPPER, Zone.BLUE);

    public UpperBlueStation(Game game) {
        super(game);
    }


    @Override
    protected void executeAButton(ICrewMember crewMember) {

    }

    @Override
    protected void executeBButton(ICrewMember crewMember) {
        game.getShield(Zone.BLUE).chargeFrom(game.getReactor(Zone.BLUE));
    }

    @Override
    protected void executeCButton(ICrewMember crewMember) {

    }
}
