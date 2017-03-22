package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;

public class PaddysPub extends Holding {
	
	public PaddysPub() {
		super(15, "Paddy's Pub", 1, 0, 0);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		changeMarkers(thePlayer.getCars(), "");
	}
}