package carnivalOfMonsters.core.events;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.Player;

public class Gain2Crowns extends Event{

	public Gain2Crowns(String name) {
		super(name);
	}

	@Override
	public void onPlay(Player playingPlayer, Game game) {
		playingPlayer.gainCrowns(2);
	}

}
