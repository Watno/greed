package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.core.lands.DistantLand;

public class TwoPerDistandLand extends SecretGoal {

	@Override
	public int score(Player player) {
		return (int) player.getCardsInPlay().stream()
				.filter(x -> x instanceof DistantLand)
				.count() * 2;
	}

}
