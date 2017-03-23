package greed.game.cards;

import greed.cards.effects.MasterPlanEvent;
import greed.game.Action;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

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