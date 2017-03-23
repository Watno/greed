package greed.game.cards;

import greed.cards.effects.ComplexSchemeEvent;
import greed.game.Action;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

public class ComplexScheme extends Action {

	public ComplexScheme() {
		super(43, "Complex Scheme!");
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return (thePlayer.getKeys()>=1 && thePlayer.getWrenches()>=1);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		theGame.addNextTurnEvent(new ComplexSchemeEvent(theGame, thePlayer, timingNumber, this));
	}
	
	@Override
	protected void discardIfNoNextTurnEffect(GreedPlayer thePlayer, GreedGame theGame) {
		
	}
	
	
	
}