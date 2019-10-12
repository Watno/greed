package spacealert.core;

import java.util.Collection;

public class MissionStepSequence {
	private Collection<IMissionStep> phases;
	
	public void execute(Game game) {
		for(IMissionStep phase: phases) {
			phase.execute(game);
		}
	}
}
