package greed.game.cards;

import greed.game.Action;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.cards.effects.ComplexSchemeEvent;

public class ComplexScheme extends Action {

	public ComplexScheme() {
		super(43, "Complex Scheme!");
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return (thePlayer.getKeys()>=1 && thePlayer.getWrenches()>=1);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
		theGame.addNextTurnEvent(new ComplexSchemeEvent(theGame, thePlayer, timingNumber, executingCard));
	}
	
	@Override
	protected void discardIfNoNextTurnEffect(GreedPlayer thePlayer, GreedGame theGame) {
		
	}
	
	
	
}