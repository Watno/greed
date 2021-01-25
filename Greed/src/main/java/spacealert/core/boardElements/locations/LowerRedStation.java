package spacealert.core.boardElements.locations;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.BoardState;
import spacealert.core.ICrewMemberFromBoardStatePerspective;
import spacealert.core.boardElements.battleBots.BattleBotStorage;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;

public class LowerRedStation extends Location {
    @JsonProperty
    private final BattleBotStorage battleBotStorage = new BattleBotStorage();

    LowerRedStation() {
        super(new Position(Deck.LOWER, Zone.RED));
    }

    @Override
    protected void executeBButton(BoardState boardState, ICrewMemberFromBoardStatePerspective crewMember) {
        boardState.getReactor(Zone.RED).chargeFrom(boardState.getReactor(Zone.WHITE));
    }

    @Override
    protected void executeCButton(BoardState boardState, ICrewMemberFromBoardStatePerspective crewMember) {
        crewMember.useBattleBotStorage(battleBotStorage);
    }
}
