package greed.game.cards;

import greed.game.Action;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

public class SuckerConvention extends Action {

	public SuckerConvention() {
		super(34, "Sucker convention!");
	}
	
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		thePlayer.changeCash(30000, this);
		for (GreedPlayer otherPlayer: theGame.getPlayers()) {
			if (!otherPlayer.equals(thePlayer)) {
				otherPlayer.changeCash(10000, this);
			}
		}
	}
	
}