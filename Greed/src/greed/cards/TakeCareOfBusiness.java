package greed.cards;

import greed.Action;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;
import greed.cards.effects.TakeCareOfBusinessEvent;

public class TakeCareOfBusiness extends Action {

	public TakeCareOfBusiness() {
		super(40, "Take care of business!");
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) { 
		for(Holding theHolding : thePlayer.getHoldings()) {
			if (theHolding.getMarkers()<=1) {
				theHolding.changeMarkers(1, "");
			}
		}
		theGame.addNextTurnEvent(new TakeCareOfBusinessEvent(theGame, thePlayer, timingNumber, this));
	}
	
	@Override
	protected void discardIfNoNextTurnEffect(GreedPlayer thePlayer, GreedGame theGame) {
		
	}
	
}