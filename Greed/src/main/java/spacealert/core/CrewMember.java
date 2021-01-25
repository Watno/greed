package spacealert.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.actionCards.ActionBoard;
import spacealert.core.boardElements.battleBots.BattleBot;
import spacealert.core.boardElements.battleBots.BattleBotStorage;
import spacealert.core.boardElements.locations.ILocation;
import spacealert.core.boardElements.positions.Direction;
import spacealert.core.boardElements.positions.Zone;

import java.util.Optional;

public class CrewMember implements ICrewMemberFromBoardStatePerspective {
    protected final ActionBoard actionBoard;
    @JsonProperty
    protected final Color color;
    @JsonProperty
    private Optional<BattleBot> battleBot = Optional.empty();
    @JsonProperty
    private boolean knockedOut = false;
    private ILocation location;


    protected CrewMember(ActionBoard actionBoard, Color color) {
        this.actionBoard = actionBoard;
        this.color = color;
    }

    public boolean hasColor(Color color) {
        return this.color == color;
    }

    @Override
    public void executeAction(int turn, BoardState boardState) {
        if (!knockedOut) actionBoard.execute(turn, this, boardState);
    }

    @Override
    public void delay() {
        actionBoard.delay();
    }

    @Override
    public void moveTo(ILocation newLocation) {
        if (location != null) {
            //might be null during initialization
            location.removeCrewMember(this);
        }
        newLocation.addCrewMember(this);
        location = newLocation;
    }

    @Override
    public void moveInDirection(BoardState boardState, Direction direction) {
        var newLocation = boardState.getAdjacentInDirection(location, direction);
        newLocation.ifPresent(this::moveTo);
    }

    @Override
    public ILocation getLocation() {
        return location;
    }

    @Override
    public Optional<Zone> getZone() {
        return location.getZone();
    }

    @Override
    public void executeButton(BoardState boardState, Button button) {
        location.executeButton(boardState, this, button);
    }

    @Override
    public boolean isInSpace() {
        return location.isSpace();
    }

    @Override
    public void useBattleBotStorage(BattleBotStorage battleBotStorage) {
        if (battleBot.isEmpty()) {
            battleBot = battleBotStorage.tryTakeBattleBotFrom();
        } else {
            battleBot.get().activate();
        }
    }

    @Override
    public boolean hasActiveBattlebot() {
        return battleBot.map(BattleBot::isActive).orElse(false);
    }

    @Override
    public void disableBattleBot() {
        battleBot.ifPresent(BattleBot::disable);
    }

    @Override
    public void becomeKnockedOut() {
        knockedOut = true;
    }

    @Override
    public int score() {
        return battleBot.map(BattleBot::score).orElse(0)
                + (knockedOut ? -1 : 0);
    }
}
