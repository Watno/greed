package greed.cards;

import greed.Action;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;

public class Raid extends Action {

	public Raid() {
		super(59, "Raid!");
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return (thePlayer.getGuns()>=1);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		for (GreedPlayer otherPlayer: theGame.getPlayers()) {
			if (otherPlayer!=thePlayer) {
				for (Holding theHolding: otherPlayer.getHoldings()) {
					theHolding.changeMarkers(-1, "");
				}
			}
		}
	}
	
	
}