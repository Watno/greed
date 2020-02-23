package carnivalOfMonsters.core.lands;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.ICanBeInPlay;
import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;

public abstract class Land implements ICanBeInPlay {

	protected Land(LandType landType, int landpoints) {
		super();
		this.landType = landType;
		this.landpoints = landpoints;
	}

	protected LandType landType;
	private int landpoints;
	
	@Override
	public int getConsumedLandPoints(LandType landType) {
		return 0;
	}
	
	@Override
	public int getProvidedLandPoints(LandType landType) {
		if (landType == this.landType) {
			return landpoints;
		}
		else return 0;
	}
	
	@Override
	public void onPlay(Player playingPlayer, Game game) {
	}

	@Override
	public int getDangerLevel() {
		return 0;
	}
}
