package spacealert.core.missionSteps;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.threats.templates.ExternalThreat;

import java.util.stream.Collectors;

public class RocketResolutionStep implements IMissionStep{

	@Override
	public GameLost execute(Game game) {
		var rocket = game.getCurrentTurnRocket();
		var externalThreats = game.getActiveThreats().stream().filter(x -> x instanceof ExternalThreat).map(x -> (ExternalThreat) x).collect(Collectors.toList());

		rocket.ifPresent(x -> x.assignDamage(externalThreats));
		for (var threat : externalThreats) {
			threat.resolveDamage(game);
		}

		return GameLost.FALSE;
	}

}
