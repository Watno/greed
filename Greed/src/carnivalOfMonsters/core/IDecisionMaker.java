package carnivalOfMonsters.core;

import java.util.Map;

public interface IDecisionMaker {

	Map<LandType, Integer> assignLandpoints(int requiredLandpoints);

	LandType chooseLandTypeForExplorer();
}
