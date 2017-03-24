package greed.game.cards;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;

public class PaddysPub extends Holding {
	
	public PaddysPub() {
		super(15, "Paddy's Pub", 1, 0, 0);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
		changeMarkers(thePlayer.getCars(), executingCard);
	}
}