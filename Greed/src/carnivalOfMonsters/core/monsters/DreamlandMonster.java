package carnivalOfMonsters.core.monsters;

import java.util.Map;
import java.util.stream.Stream;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;

public abstract class DreamlandMonster extends Monster {

	private Map<LandType, Integer> assignedLandpoints;

	protected DreamlandMonster(int level, int dangerLevel, int monstrousLore, int victoryPoints) {
		super(LandType.DREAMLANDS, level, dangerLevel, monstrousLore, victoryPoints);
	}

	@Override
	public int getConsumedLandPoints(LandType landType) {
		if (assignedLandpoints == null)
			return 0;
		return assignedLandpoints.getOrDefault(landType, 0);
	}

	@Override
	public boolean checkRequirement(Player playingPlayer) {
		return Stream.of(LandType.values()).mapToInt(x -> playingPlayer.getAvailableLandPoints(x)).sum() >= level;
	}

	@Override
	public void onPlay(Player playingPlayer, Game game) {
		assignedLandpoints = playingPlayer.getDecisionMaker().assignLandpoints(level);
		super.onPlay(playingPlayer, game);
	}

}
