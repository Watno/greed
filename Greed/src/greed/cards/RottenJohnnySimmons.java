package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Thug;
import greed.eventypes.IgnoreNeedEvent;

public class RottenJohnnySimmons extends Thug {
	
	public RottenJohnnySimmons() {
		super(64, "\"Rotten\" Johnny Simmons", 1, 0, 0);
	}
	
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame)  {
		theGame.addNextTurnEvent(new IgnoreNeedEvent(theGame, thePlayer, timingNumber, this));
	}
}