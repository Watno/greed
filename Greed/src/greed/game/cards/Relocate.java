package greed.game.cards;

import greed.game.Action;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;

public class Relocate extends Action {
	private int markers=0;

	public Relocate() {
		super(41, "Relocate!");
	}
	
	@Override
	public boolean payCost(GreedPlayer thePlayer, GreedGame theGame) {
		Holding theHolding = thePlayer.payHolding(theGame, this);
		if (theHolding!=null) {
			markers = theHolding.getMarkersBeforeRemove();
			if (theHolding.getLocation()==theGame.getDiscardPile()) {
				theGame.getDiscardPile().remove(theHolding);
				thePlayer.getHand().add(theHolding);
				theHolding.setLocation(thePlayer.getHand());
			}
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
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		theGame.addNextTurnEvent(new RelocateEvent(theGame, thePlayer, timingNumber, this, markers+2));
	}
	
	@Override
	protected void discardIfNoNextTurnEffect(GreedPlayer thePlayer, GreedGame theGame) {
		
	}
	
}