package spacealert.core.boardElements.locations;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.BoardState;
import spacealert.core.ICrewMemberFromBoardStatePerspective;
import spacealert.core.boardElements.battleBots.BattleBotStorage;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;

public class UpperBlueStation extends Location {
    @JsonProperty
    private final BattleBotStorage battleBotStorage = new BattleBotStorage();

    UpperBlueStation() {
        super(new Position(Deck.UPPER, Zone.BLUE));
    }

    @Override
    protected void executeBButton(BoardState boardState, ICrewMemberFromBoardStatePerspective crewMember) {
        boardState.getShield(Zone.BLUE).chargeFrom(boardState.getReactor(Zone.BLUE));
    }

    @Override
    protected void executeCButton(BoardState boardState, ICrewMemberFromBoardStatePerspective crewMember) {
        crewMember.useBattleBotStorage(battleBotStorage);
    }
}
