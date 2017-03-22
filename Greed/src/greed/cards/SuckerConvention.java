package greed.cards;

import greed.Action;
import greed.GreedGame;
import greed.GreedPlayer;

public class SuckerConvention extends Action {

	public SuckerConvention() {
		super(34, "Sucker convention!");
	}
	
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		thePlayer.changeCash(30000, "");
		for (GreedPlayer otherPlayer: theGame.getPlayers()) {
			if (!otherPlayer.equals(thePlayer)) {
				otherPlayer.changeCash(10000, "");
			}
		}
	}
	
}