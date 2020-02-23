package carnivalOfMonsters.core.events;

import carnivalOfMonsters.core.ICanBePlayed;
import carnivalOfMonsters.core.Player;

public abstract class Event implements ICanBePlayed {

	@Override
	public boolean checkRequirement(Player playingPlayer) {
		return true;
	}

}
