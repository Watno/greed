package spacealert.core;

import spacealert.core.boardElements.battleBots.BattleBotStorage;
import spacealert.core.boardElements.locations.ILocation;
import spacealert.core.boardElements.positions.Direction;
import spacealert.core.boardElements.positions.Zone;

import java.util.Optional;

public interface ICrewMember {

    void executeAction(int turn, Game game);

	void delay();

    void moveTo(ILocation newLocation);

    void moveInDirection(Game game, Direction direction);

	ILocation getLocation();

    Optional<Zone> getZone();

    void executeButton(Game game, Button button);

    boolean isInSpace();

    void useBattleBotStorage(BattleBotStorage battleBotStorage);

    boolean hasActiveBattlebot();

    void disableBattleBot();

    void becomeKnockedOut();
}
