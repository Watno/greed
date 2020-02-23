package carnivalOfMonsters.core.events;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.Player;

public class GainHunterToken extends Event {

	@Override
	public void onPlay(Player playingPlayer, Game game) {
		playingPlayer.gainHunterToken();
	}

}
