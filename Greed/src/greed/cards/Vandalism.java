package greed.cards;

import greed.Action;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.cards.effects.VandalismEvent;

public class Vandalism extends Action {

	public Vandalism() {
		super(79, "Vandalism!");
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return thePlayer.getCars()>=1;
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		theGame.addNextTurnEvent(new VandalismEvent(theGame, thePlayer, timingNumber, this));
	}
	
	@Override
	protected void discardIfNoNextTurnEffect(GreedPlayer thePlayer, GreedGame theGame) {
		
	}
	
}