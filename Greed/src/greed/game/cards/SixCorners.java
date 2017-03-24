package greed.game.cards;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;

public class SixCorners extends Holding {
	
	public SixCorners() {
		super(50, "Six Corners", 0, 0, 0);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
		for (Holding theHolding : thePlayer.getHoldings()) {
			if (theHolding.getBottles()==0 && theHolding.getHearts()==0 && theHolding.getWrenches()==0) {
				theHolding.changeMarkers(1, executingCard);
			}
		}
	}
}