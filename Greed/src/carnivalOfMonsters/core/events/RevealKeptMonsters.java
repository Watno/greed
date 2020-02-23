package carnivalOfMonsters.core.events;

import java.util.stream.Stream;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.core.monsters.Monster;

public class RevealKeptMonsters extends Event {

	@Override
	public void onPlay(Player playingPlayer, Game game) {
		//TODO add revelation (and choice)
		
		playingPlayer.gainCrowns((int) Stream.of(playingPlayer.getMenagerie())
			.filter(x -> x instanceof Monster)
			.count());
		
	}

}
