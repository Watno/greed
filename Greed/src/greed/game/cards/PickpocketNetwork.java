package greed.game.cards;

import greed.game.Action;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

public class PickpocketNetwork extends Action {

	public PickpocketNetwork() {
		super(31, "Pickpocket network!");
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		thePlayer.gainCash(thePlayer.getKeys()*10000, "");
		thePlayer.gainCash(thePlayer.getWrenches()*10000, "");
	}
}
