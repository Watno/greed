package greed.cards;

import greed.Action;
import greed.GreedGame;
import greed.GreedPlayer;

public class HonestWork extends Action {

	public HonestWork() {
		super(36, "Honest work!");
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		thePlayer.gainCash(15000, "");
		if (thePlayer.getCash()<50000) {
			if (getLocation()==thePlayer.getActions()) {
				thePlayer.getHand().add(this);
				setLocation(thePlayer.getHand());
				thePlayer.getActions().remove(this);
			}
		}
	}
}