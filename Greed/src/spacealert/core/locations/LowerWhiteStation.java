package spacealert.core.locations;

import spacealert.core.*;

public class LowerWhiteStation extends Location {
    private int availableFuelCapsules = 3;

    public LowerWhiteStation() {
        super(new Position(Deck.LOWER, Zone.WHITE));
    }

    @Override
    protected void executeAButton(Game game, ICrewMember crewMember) {

    }

    @Override
    protected void executeBButton(Game game, ICrewMember crewMember) {
        if (availableFuelCapsules > 0) {
            game.getReactor(Zone.WHITE).loadToFull();
        }
    }

    @Override
    protected void executeCButton(Game game, ICrewMember crewMember) {

    }
}
