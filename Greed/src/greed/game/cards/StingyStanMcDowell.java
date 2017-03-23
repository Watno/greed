package greed.game.cards;

import greed.cards.effects.StingyStanMcDowellEvent;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;

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