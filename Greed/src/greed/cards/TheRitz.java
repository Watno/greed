package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;

public class TheRitz extends Holding {
	
	public TheRitz() {
		super(18, "The Ritz", 1, 0, 0);
	}
	
	@Override
	public boolean payCost(GreedPlayer thePlayer, GreedGame theGame) {
		return thePlayer.payCashCost(30000, this);
	}
	
	@Override
	public boolean isPlayable(GreedPlayer thePlayer, GreedGame theGame) {
		super.isPlayable(thePlayer, theGame);
		return thePlayer.getCash()>=30000;
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		changeMarkers(5, "");
	}
}