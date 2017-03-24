package greed.game.cards;

import greed.game.Action;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

public class SuckerConvention extends Action {

	public SuckerConvention() {
		super(34, "Sucker convention!");
	}
	
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
		thePlayer.changeCash(30000, executingCard);
		for (GreedPlayer otherPlayer: theGame.getPlayers()) {
			if (!otherPlayer.equals(thePlayer)) {
				otherPlayer.changeCash(10000, executingCard);
			}
		}
	}
	
}