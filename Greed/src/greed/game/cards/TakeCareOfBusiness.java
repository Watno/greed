package greed.game.cards;

import greed.game.Action;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;
import greed.game.cards.effects.TakeCareOfBusinessEvent;

public class TakeCareOfBusiness extends Action {

	public TakeCareOfBusiness() {
		super(40, "Take care of business!");
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) { 
		for(Holding theHolding : thePlayer.getHoldings()) {
			if (theHolding.getMarkers()<=1) {
				theHolding.changeMarkers(1, this);
			}
		}
		theGame.addNextTurnEvent(new TakeCareOfBusinessEvent(theGame, thePlayer, timingNumber, this));
	}
	
	@Override
	protected void discardIfNoNextTurnEffect(GreedPlayer thePlayer, GreedGame theGame) {
		
	}
	
}