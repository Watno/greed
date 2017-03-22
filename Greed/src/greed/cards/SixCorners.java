package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;

public class SixCorners extends Holding {
	
	public SixCorners() {
		super(50, "Six Corners", 0, 0, 0);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		for (Holding theHolding : thePlayer.getHoldings()) {
			if (theHolding.getBottles()==0 && theHolding.getHearts()==0 && theHolding.getWrenches()==0) {
				theHolding.changeMarkers(1, "");
			}
		}
	}
}