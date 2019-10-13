package spacealert.core;

import spacealert.core.actionCards.ActionBoard;

public class CrewMember implements ICrewMember{
	private ActionBoard actionBoard;
	private ILocation location;
	
	@Override
	public void executeAction(int turn) {
		actionBoard.execute(turn, this);
	}
	
	@Override
	public void delay(int delayedTurn) {
		actionBoard.delay(delayedTurn);
	}
	
	public void moveTo(ILocation location) {
		
	}
}
