package greed.cards;

import greed.Action;
import greed.GreedGame;
import greed.GreedPlayer;

public class BeggarsBanquet extends Action {

	public BeggarsBanquet() {
		super(20, "Beggars Banquet!");
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		int numberOfThugs = thePlayer.getNumberOfThugs();
		for (GreedPlayer otherPlayer : theGame.getPlayers()) {
			if (!otherPlayer.equals(thePlayer)) {
				if (otherPlayer.getNumberOfThugs()<=numberOfThugs) {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		thePlayer.gainCash(25000, "");
	}
	
}