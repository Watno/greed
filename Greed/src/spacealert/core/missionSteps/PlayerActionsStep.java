package spacealert.core.missionSteps;

import spacealert.core.Game;
import spacealert.core.ICrewMember;

public class PlayerActionsStep implements IMissionStep {
	private int turn;
	
	public PlayerActionsStep(int turn) {
		super();
		this.turn = turn;
	}
	
	@Override
	public void execute(Game game) {
		for(ICrewMember crewMember: game.getCrewMembers()) {
			crewMember.executeAction(turn, game);
		}

	}

}
