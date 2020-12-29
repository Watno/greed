package spacealert.core.boardElements.locations;

import spacealert.core.Game;
import spacealert.core.ICrewMember;
import spacealert.core.boardElements.battleBots.BattleBotStorage;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;

public class UpperBlueStation extends Location {
    private BattleBotStorage battleBotStorage = new BattleBotStorage();

    UpperBlueStation() {
        super(new Position(Deck.UPPER, Zone.BLUE));
    }

    @Override
    protected void executeBButton(Game game, ICrewMember crewMember) {
        game.getShield(Zone.BLUE).chargeFrom(game.getReactor(Zone.BLUE));
    }

    @Override
    protected void executeCButton(Game game, ICrewMember crewMember) {
        crewMember.useBattleBotStorage(battleBotStorage);
    }
}
