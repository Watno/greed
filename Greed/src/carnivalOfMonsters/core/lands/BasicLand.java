package carnivalOfMonsters.core.lands;

import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;

public abstract class BasicLand extends Land{

	protected BasicLand(LandType landType, int landpoints) {
		super(landType, landpoints);
	}

	@Override
	public boolean checkRequirement(Player playingPlayer) {
		return true;
	}

}
