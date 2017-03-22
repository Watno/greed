package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;

public class JoesGinJoint extends Holding {
	
	public JoesGinJoint() {
		super(11, "Joe's Gin Joint", 1, 0, 0);
	}
	
	@Override
	public boolean payCost(GreedPlayer thePlayer, GreedGame theGame) {
		return thePlayer.payCashCost(15000, this);
	}
	
	@Override
	public boolean isPlayable(GreedPlayer thePlayer, GreedGame theGame) {
		super.isPlayable(thePlayer, theGame);
		return thePlayer.getCash()>=15000;
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		changeMarkers(2, "");
	}
}
