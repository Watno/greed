package spacealert.core;

import spacealert.core.actionCards.ActionBoard;

public class CrewMember implements ICrewMember{
	private ActionBoard actionBoard;
	
	@Override
	public void executeAction(int turn) {
		actionBoard.execute(turn, this);
	}
}
