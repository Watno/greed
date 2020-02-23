package carnivalOfMonsters.core.secretGoals;

import java.util.stream.Stream;

import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;

public class SevenIfSevenInLandType extends SecretGoal {

	@Override
	public int score(Player player) {
		if (Stream.of(LandType.values())
				.anyMatch(x -> player.getAvailableLandPoints(x) >= 7)) {
			return 7;
		}
		return 0;
	}

}
