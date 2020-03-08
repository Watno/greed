package carnivalOfMonsters.core.events;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;

public class GainPerLandPoint extends Event{

	private LandType landType;
	
	public GainPerLandPoint(String name, LandType landType) {
		super(name);
		this.landType = landType;
	}
	
	@Override
	public void onPlay(Player playingPlayer, Game game) {
		playingPlayer.gainCrowns(playingPlayer.getAvailableLandPoints(landType));
	}

}
