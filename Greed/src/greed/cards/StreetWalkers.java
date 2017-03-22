package greed.cards;

import greed.Action;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;

public class StreetWalkers extends Action {

	public StreetWalkers() {
		super(39, "Street walkers!");
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return (thePlayer.getHearts()>=1);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		for(Holding theHolding: thePlayer.getHoldings()) {
			theHolding.changeMarkers(1, "");
		}
	}
}
