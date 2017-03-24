package greed.game.cards;

import greed.game.Action;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;
import greed.game.cards.effects.InsuranceScamEvent;

public class InsuranceScam extends Action {

	public InsuranceScam() {
		super(5, "Insurance Scam!");
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return thePlayer.getKeys()>=1;
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
		int mostMarkers = 0;
		for (Holding theHolding : thePlayer.getHoldings()) {
			mostMarkers = Math.max(mostMarkers, theHolding.getMarkers());
		}
		thePlayer.gainCash(mostMarkers*5000, executingCard);
		theGame.addNextTurnEvent(new InsuranceScamEvent(theGame, thePlayer, timingNumber, executingCard));
	}
	
	@Override
	protected void discardIfNoNextTurnEffect(GreedPlayer thePlayer, GreedGame theGame) {
		
	}
	
}