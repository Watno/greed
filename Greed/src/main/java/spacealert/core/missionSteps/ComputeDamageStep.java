package spacealert.core.missionSteps;

import spacealert.core.Game;
import spacealert.core.GameLost;

public class ComputeDamageStep implements IMissionStep {

	@Override
	public GameLost execute(Game game) {
		var damageSources = game.getDamageSources();
		var threats = game.getActiveThreats();

		for (var damageSource : damageSources) {
			damageSource.assignDamage(threats, game);
		}
		for (var threat : threats) {
			var gameLost = threat.resolveDamage(game);
			if (gameLost == GameLost.TRUE) return GameLost.TRUE;
		}
		return GameLost.FALSE;
	}

}
