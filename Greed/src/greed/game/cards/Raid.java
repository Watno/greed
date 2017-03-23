package greed.game.cards;

import greed.game.Action;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;

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
					theHolding.changeMarkers(-1, this);
				}
			}
		}
	}
	
	
}