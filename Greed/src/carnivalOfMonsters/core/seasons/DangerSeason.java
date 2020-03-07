package carnivalOfMonsters.core.seasons;

import carnivalOfMonsters.core.ICanBePlayed;
import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.core.monsters.Monster;

public class DangerSeason extends Season {

	@Override
	public boolean triggersOn(ICanBePlayed card) {
		if (!(card instanceof Monster)) return false;
		var monster = (Monster) card;
		return monster.getDangerLevel() > 0;
	}

	@Override
	protected int getCompareValue(Player player) {
		return player.getCardsInPlay().stream()
			.filter(x -> x instanceof Monster)
			.map(x -> (Monster) x)
			.mapToInt(x -> x.getDangerLevel())
			.sum();
	}

}
