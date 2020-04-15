package spacealert.core.missionSteps;

import spacealert.core.Game;
import spacealert.core.threats.ExternalThreat;

import java.util.stream.Collectors;

public class ComputeDamageStep implements IMissionStep {

	@Override
	public void execute(Game game) {
		var damageSources = game.getDamageSources();
		var externalThreats = game.getActiveThreats().stream().filter(x -> x instanceof ExternalThreat).map(x -> (ExternalThreat) x).collect(Collectors.toList());

		for (var damageSource : damageSources) {
			damageSource.assignDamage(externalThreats);
		}
		for (var threat : externalThreats) {
			threat.resolveDamage(game);
		}
	}

}
