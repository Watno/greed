package carnivalOfMonsters.core.seasons;

import carnivalOfMonsters.core.ICanBePlayed;
import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.core.monsters.Monster;

public class LandTypeSeason extends Season {

	public LandTypeSeason(LandType landType) {
		super();
		this.landType = landType;
	}

	private LandType landType;
	
	@Override
	public boolean triggersOn(ICanBePlayed card) {
		if (!(card instanceof Monster)) return false;
		var monster = (Monster) card;
		return monster.getLandType() == landType;
	}
	
	@Override
	protected int getCompareValue(Player player) {
		return (int) player.getCardsInPlay().stream()
			.filter(x -> x instanceof Monster)
			.map(x -> (Monster) x)
			.filter(x -> x.getLandType().equals(this.landType))
			.count();
	}

}
