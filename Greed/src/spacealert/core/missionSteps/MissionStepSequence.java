package spacealert.core.missionSteps;

import java.util.List;

import spacealert.core.Game;

public class MissionStepSequence {
	private List<IMissionStep> missionSteps;	
	
	protected MissionStepSequence(List<IMissionStep> missionSteps) {
		super();
		this.missionSteps = missionSteps;
	}

	public void execute(Game game) {
		for(IMissionStep phase: missionSteps) {
			phase.execute(game);
		}
	}
}
