package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;

public class Headquarters extends Holding {
	
	public Headquarters() {
		super(37, "Headquarters", 0, 0, 0);
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return (thePlayer.getGuns()>=1 && thePlayer.getKeys()>=1);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		changeMarkers(thePlayer.getNumberOfHoldings(), "");
	}
}
