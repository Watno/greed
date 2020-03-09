package carnivalOfMonsters.core.seasons;

import carnivalOfMonsters.core.ICanBePlayed;
import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.core.monsters.Monster;

public class DangerSeason extends Season {

	@Override
	public boolean triggersOn(Player player, ICanBePlayed card) {
		if (!(card instanceof Monster)) return false;
		var monster = (Monster) card;
		if (monster.getDangerLevel() == 0) return false;
		if (player.getCardsInPlay().stream().filter(x -> x instanceof Monster).map(x -> (Monster)x).anyMatch(x -> x.getDangerLevel() > 0)) return false;
		return true;
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
