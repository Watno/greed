package carnivalOfMonsters.core.monsters;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.ICanBeInPlay;
import carnivalOfMonsters.core.ICanBeScored;
import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;

public abstract class Monster implements ICanBeInPlay, ICanBeScored {

	protected LandType landType;
	protected int level;
	
	private int dangerLevel;
	private int monstrousLore;
	
	private int victoryPoints;
	
	protected Monster(LandType landType, int level, int dangerLevel, int monstrousLore, int victoryPoints) {
		super();
		this.landType = landType;
		this.level = level;
		this.dangerLevel = dangerLevel;
		this.monstrousLore = monstrousLore;
		this.victoryPoints = victoryPoints;
	}

	@Override
	public int getProvidedLandPoints(LandType landType) {
		return 0;
	}
	
	@Override
	public void onPlay(Player playingPlayer, Game game) {
		playingPlayer.draw(game, monstrousLore);
	}

	public LandType getLandType() {
		return landType;
	}
	
	public int getLevel() {
		return level;
	}
	
	@Override
	public int getDangerLevel() {
		return dangerLevel;
	}
	
	@Override
	public int score(Player player) {
		return victoryPoints;
	}
}
