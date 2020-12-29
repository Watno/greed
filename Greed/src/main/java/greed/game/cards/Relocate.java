package greed.game.cards;

import greed.game.*;
import greed.game.cards.effects.RelocateEvent;

public class Relocate extends Action {
	private int markers=0;

	public Relocate() {
		super(41, "Relocate!");
	}
	
	@Override
	public boolean payCost(GreedPlayer thePlayer, GreedGame theGame) {
		Holding theHolding = thePlayer.payHolding(this);
		if (theHolding!=null) {
			markers = theHolding.getMarkersBeforeRemove();
			theHolding.putBackInHand(thePlayer, theGame, this, theGame.getDiscardPile());
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isPlayable(GreedPlayer thePlayer, GreedGame theGame) {
		super.isPlayable(thePlayer, theGame);
		return thePlayer.getNumberOfHoldings()>=1;
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
		theGame.addNextTurnEvent(new RelocateEvent(theGame, thePlayer, timingNumber, executingCard, markers+2));
	}
	
	@Override
	protected void discardIfNoNextTurnEffect(GreedPlayer thePlayer, GreedGame theGame) {
		
	}
	
}