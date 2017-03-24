package greed.game.cards;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;
import greed.game.eventtypes.IgnoreNeedEvent;

public class RottenJohnnySimmons extends Thug {
	
	public RottenJohnnySimmons() {
		super(64, "\"Rotten\" Johnny Simmons", 1, 0, 0);
	}
	
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard)  {
		theGame.addNextTurnEvent(new IgnoreNeedEvent(theGame, thePlayer, timingNumber, executingCard));
	}
}