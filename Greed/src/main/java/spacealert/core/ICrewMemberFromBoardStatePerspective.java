package spacealert.core;

import spacealert.core.boardElements.battleBots.BattleBotStorage;
import spacealert.core.boardElements.locations.ILocation;
import spacealert.core.boardElements.positions.Direction;
import spacealert.core.boardElements.positions.Zone;

import java.util.Optional;

public interface ICrewMemberFromBoardStatePerspective {

    void executeAction(int turn, BoardState boardState);

    void delay();

    void moveTo(ILocation newLocation);

    void moveInDirection(BoardState boardState, Direction direction);

    ILocation getLocation();

    Optional<Zone> getZone();

    void executeButton(BoardState boardState, Button button);

    boolean isInSpace();

    void useBattleBotStorage(BattleBotStorage battleBotStorage);

    boolean hasActiveBattlebot();

    void disableBattleBot();

    void becomeKnockedOut();

    int score();
}
