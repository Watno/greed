package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Thug;
import greed.cards.effects.StingyStanMcDowellEvent;

public class StingyStanMcDowell extends Thug {
	
	public StingyStanMcDowell() {
		super(66, "\"Stingy\" Stan McDowell", 1, 0, 0);
	}
	
	
	@Override
	protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
		super.putInPlay(thePlayer, theGame);
		addPermanentEffect(new StingyStanMcDowellEvent(theGame, thePlayer, timingNumber, this));
	}
}