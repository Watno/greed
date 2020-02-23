package carnivalOfMonsters.core.staff;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.ICanBeInPlay;
import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;

public abstract class StaffMember implements ICanBeInPlay {

	private int cost;
	
	protected StaffMember(int cost) {
		super();
		this.cost = cost;
	}

	@Override
	public boolean checkRequirement(Player playingPlayer) {
		return true;
	}

	@Override
	public void onPlay(Player playingPlayer, Game game) {
		playingPlayer.pay(cost);
		
	}

	@Override
	public int getProvidedLandPoints(LandType landType) {
		return 0;
	}

	@Override
	public int getConsumedLandPoints(LandType landType) {
		return 0;
	}

	@Override
	public int getDangerLevel() {
		return 0;
	}

}
