package spacealert.core.missionSteps;

import spacealert.core.Game;
import spacealert.core.GameLost;

public class RocketResolutionStep implements IMissionStep{

	@Override
	public GameLost execute(Game game) {
        var rocket = game.getCurrentTurnRocket();
        var threats = game.getActiveThreats();

        rocket.ifPresent(x -> x.assignDamage(threats));
        for (var threat : threats) {
            threat.resolveDamage(game);
        }

        return GameLost.FALSE;
    }

}
