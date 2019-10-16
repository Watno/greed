package spacealert.core.locations;

import spacealert.core.*;

public class UpperBlueStation extends Location {

    public UpperBlueStation() {
        super(new Position(Deck.UPPER, Zone.BLUE));
    }


    @Override
    protected void executeAButton(Game game, ICrewMember crewMember) {

    }

    @Override
    protected void executeBButton(Game game, ICrewMember crewMember) {
        game.getShield(Zone.BLUE).chargeFrom(game.getReactor(Zone.BLUE));
    }

    @Override
    protected void executeCButton(Game game, ICrewMember crewMember) {

    }
}
