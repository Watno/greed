package greed.cards;

import greed.Action;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;
import greed.cards.effects.InsuranceScamEvent;

public class InsuranceScam extends Action {

	public InsuranceScam() {
		super(5, "Insurance Scam!");
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return thePlayer.getKeys()>=1;
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		int mostMarkers = 0;
		for (Holding theHolding : thePlayer.getHoldings()) {
			mostMarkers = Math.max(mostMarkers, theHolding.getMarkers());
		}
		thePlayer.gainCash(mostMarkers*5000, "");
		theGame.addNextTurnEvent(new InsuranceScamEvent(theGame, thePlayer, timingNumber, this));
	}
	
	@Override
	protected void discardIfNoNextTurnEffect(GreedPlayer thePlayer, GreedGame theGame) {
		
	}
	
}