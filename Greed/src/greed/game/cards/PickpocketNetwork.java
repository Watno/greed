package greed.game.cards;

import greed.game.Action;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

public class PickpocketNetwork extends Action {

	public PickpocketNetwork() {
		super(31, "Pickpocket network!");
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
		thePlayer.gainCash(thePlayer.getKeys()*10000, executingCard);
		thePlayer.gainCash(thePlayer.getWrenches()*10000, executingCard);
	}
}
