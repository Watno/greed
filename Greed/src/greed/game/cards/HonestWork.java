package greed.game.cards;

import greed.game.Action;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

public class HonestWork extends Action {

	public HonestWork() {
		super(36, "Honest work!");
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		thePlayer.gainCash(15000, this);
		if (thePlayer.getCash()<50000) {
			putBackInHand(thePlayer, theGame, this, thePlayer.getActions());
		}
	}
}