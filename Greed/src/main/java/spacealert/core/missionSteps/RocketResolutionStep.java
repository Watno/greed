package spacealert.core.missionSteps;

import spacealert.core.Game;
import spacealert.core.GameLost;

public class RocketResolutionStep implements IMissionStep{

	@Override
	public GameLost execute(Game game) {
        var rocket = game.getCurrentTurnRocket();
        var threats = game.getActiveThreats();

        rocket.ifPresent(x -> x.assignDamage(threats, game));
        for (var threat : threats) {
            var gameLost = threat.resolveDamage(game);
            if (gameLost == GameLost.TRUE) return GameLost.TRUE;
        }
        return GameLost.FALSE;
    }

}
