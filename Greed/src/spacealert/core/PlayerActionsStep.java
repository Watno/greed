package spacealert.core;

import spacealert.core.missionSteps.IMissionStep;

public class PlayerActionsStep implements IMissionStep {
	private int turnNumber;
	
	public PlayerActionsStep(int turnNumber) {
		super();
		this.turnNumber = turnNumber;
	}
	
	@Override
	public void execute(Game game) {
		for(ICrewMember crewMember: game.getCrewMembers())
			crewMember.executeAction(turnNumber);
	}

}
