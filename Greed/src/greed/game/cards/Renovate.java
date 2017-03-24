package greed.game.cards;

import greed.game.Action;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;

public class Renovate extends Action {

	public Renovate() {
		super(38, "Renovate!");
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
		Holding theHolding = thePlayer.chooseHolding(this);
		if (theHolding!=null) {
			theHolding.changeMarkers(2, executingCard);
		}
	}
	
	
}