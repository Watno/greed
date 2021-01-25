package spacealert.core.boardElements.locations;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.BoardState;
import spacealert.core.ICrewMemberFromBoardStatePerspective;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;

public class LowerWhiteStation extends Location {
    @JsonProperty
    private int availableFuelCapsules = 3;

    LowerWhiteStation() {
        super(new Position(Deck.LOWER, Zone.WHITE));
    }

    @Override
    protected void executeBButton(BoardState boardState, ICrewMemberFromBoardStatePerspective crewMember) {
        if (availableFuelCapsules > 0) {
            boardState.getReactor(Zone.WHITE).loadToFull();
            availableFuelCapsules--;
        }
    }

    @Override
    protected void executeCButton(BoardState boardState, ICrewMemberFromBoardStatePerspective crewMember) {
        boardState.addVisualConfirmation();
    }
}
