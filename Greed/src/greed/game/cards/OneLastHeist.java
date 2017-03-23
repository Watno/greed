package greed.game.cards;

import greed.cards.effects.OneLastHeistEvent;
import greed.game.Action;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

public class OneLastHeist extends Action {

	public OneLastHeist() {
		super(73, "One last heist!");
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return thePlayer.getGuns()>=1;
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		int mostThugs = 0;
		for (GreedPlayer anyPlayer: theGame.getPlayers()) {
			mostThugs = Math.max(mostThugs, anyPlayer.getNumberOfThugs());
		}
		thePlayer.gainCash(mostThugs*5000, "");
		theGame.addNextTurnEvent(new OneLastHeistEvent(theGame, thePlayer, timingNumber, this));
	}
	
	@Override
	protected void discardIfNoNextTurnEffect(GreedPlayer thePlayer, GreedGame theGame) {
		
	}
	
}