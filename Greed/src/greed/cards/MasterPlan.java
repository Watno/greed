package greed.cards;

import greed.Action;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.cards.effects.MasterPlanEvent;

public class MasterPlan extends Action {

	public MasterPlan() {
		super(78, "Master Plan!");
	}
	
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		theGame.addNextTurnEvent(new MasterPlanEvent(theGame, thePlayer, timingNumber, this));
	}
	
	@Override
	protected void discardIfNoNextTurnEffect(GreedPlayer thePlayer, GreedGame theGame) {
		
	}
}