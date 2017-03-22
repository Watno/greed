package greed.cards;

import greed.Action;
import greed.GreedGame;
import greed.GreedPlayer;

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
