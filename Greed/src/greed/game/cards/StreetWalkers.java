package greed.game.cards;

import greed.game.Action;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;

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
