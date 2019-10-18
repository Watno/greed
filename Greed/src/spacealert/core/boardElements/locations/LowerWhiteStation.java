package spacealert.core.boardElements.locations;

import spacealert.core.Game;
import spacealert.core.ICrewMember;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;

public class LowerWhiteStation extends Location {
    private int availableFuelCapsules = 3;

    LowerWhiteStation() {
        super(new Position(Deck.LOWER, Zone.WHITE));
    }

    @Override
    protected void executeBButton(Game game, ICrewMember crewMember) {
        if (availableFuelCapsules > 0) {
            game.getReactor(Zone.WHITE).loadToFull();
        }
    }

    @Override
    protected void executeCButton(Game game, ICrewMember crewMember) {
        game.addVisualConfirmation();
    }
}
