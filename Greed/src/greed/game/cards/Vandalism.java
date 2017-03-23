package greed.game.cards;

import greed.cards.effects.VandalismEvent;
import greed.game.Action;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

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