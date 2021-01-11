package spacealert.core.boardElements.locations;

import spacealert.core.Game;
import spacealert.core.ICrewMember;
import spacealert.core.boardElements.battleBots.BattleBotStorage;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;

public class LowerRedStation extends Location {
    private BattleBotStorage battleBotStorage = new BattleBotStorage();

    LowerRedStation() {
        super(new Position(Deck.LOWER, Zone.RED));
    }

    @Override
    protected void executeBButton(Game game, ICrewMember crewMember) {
        game.getReactor(Zone.RED).chargeFrom(game.getReactor(Zone.WHITE));
    }

    @Override
    protected void executeCButton(Game game, ICrewMember crewMember) {
        crewMember.useBattleBotStorage(battleBotStorage);
    }
}
