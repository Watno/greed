package spacealert.core;

import spacealert.core.actionCards.ActionBoard;
import spacealert.core.boardElements.battleBots.BattleBot;
import spacealert.core.boardElements.battleBots.BattleBotStorage;
import spacealert.core.boardElements.locations.ILocation;
import spacealert.core.boardElements.positions.Direction;
import spacealert.core.boardElements.positions.Zone;

import java.util.Optional;

public class CrewMember implements ICrewMember {
	private ActionBoard actionBoard;
	private ILocation location;
	private Optional<BattleBot> battleBot = Optional.empty();
	private boolean knockedOut = false;


	CrewMember(ILocation location, ActionBoard actionBoard) {
		this.location = location;
		this.actionBoard = actionBoard;
	}

	@Override
	public void executeAction(int turn, Game game) {
		if (!knockedOut) actionBoard.execute(turn, this, game);
	}

	@Override
	public void delay() {
		actionBoard.delay();
	}

	@Override
	public void moveTo(ILocation newLocation) {
		location.removeCrewMember(this);
		newLocation.addCrewMember(this);
		location = newLocation;
	}

	@Override
	public void moveInDirection(Game game, Direction direction) {
		var newLocation = game.getAdjacentInDirection(location, direction);
		newLocation.ifPresent(this::moveTo);
	}

	@Override
	public ILocation getLocation() {
		return location;
	}

	@Override
	public Optional<Zone> getZone(){
		return location.getZone();
	}

	@Override
	public void executeButton(Game game, Button button) {
		location.executeButton(game, this, button);
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
}
