package carnivalOfMonsters.core;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MockDecisionMaker implements IDecisionMaker {
	private Player player;
	
	@Override
	public Map<LandType, Integer> assignLandpoints(int requiredLandpoints) {
		Map<LandType, Integer> result = Map.of();
		for (var landType: Stream.of(LandType.values()).sorted(Comparator.comparing(x -> x.equals(LandType.DREAMLANDS))).collect(Collectors.toList())) {
			var assignableLandpoints = Integer.min(requiredLandpoints, player.getAvailableLandPoints(landType));
			result.put(landType, assignableLandpoints);
			requiredLandpoints -= assignableLandpoints;
		}
		return result;
	}

	@Override
	public LandType chooseLandTypeForExplorer() {
		return LandType.ENCHANTEDFOREST;
	}
}
