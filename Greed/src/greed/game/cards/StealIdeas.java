package greed.game.cards;

import greed.game.Action;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;

public class StealIdeas extends Action {

	public StealIdeas() {
		super(56, "Steal Ideas!");
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return (thePlayer.getWrenches()>=1);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		int mostMarkers = 0;
		for (GreedPlayer otherPlayer : theGame.getPlayers()) {
			if (otherPlayer != thePlayer) {
				for (Holding theHolding : otherPlayer.getHoldings()) {
					mostMarkers = Math.max(mostMarkers, theHolding.getMarkers());
				}
			}		
		}
		Holding holdingGettingMarkers = thePlayer.chooseHolding(this);
		if (holdingGettingMarkers!=null) {
			holdingGettingMarkers.changeMarkers(mostMarkers, this);
		}
	}
}