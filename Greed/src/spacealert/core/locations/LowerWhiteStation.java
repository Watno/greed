package spacealert.core.locations;

import spacealert.core.*;

public class LowerWhiteStation extends Location {
    private Position position = new Position(Deck.LOWER, Zone.WHITE);
    private int availableFuelCapsules = 3;

    public LowerWhiteStation(Game game) {
        super(game);
    }

    @Override
    protected void executeAButton(ICrewMember crewMember) {

    }

    @Override
    protected void executeBButton(ICrewMember crewMember) {
        if (availableFuelCapsules > 0) {
            game.getReactor(Zone.WHITE).loadToFull();
        }
    }

    @Override
    protected void executeCButton(ICrewMember crewMember) {

    }
}
