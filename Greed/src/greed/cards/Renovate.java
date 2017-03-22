package greed.cards;

import greed.Action;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;

public class Renovate extends Action {

	public Renovate() {
		super(38, "Renovate!");
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		Holding theHolding = thePlayer.chooseHolding("");
		if (theHolding!=null) {
			theHolding.changeMarkers(2, "");
		}
	}
	
	
}