package greed.game.cards;

import greed.cards.effects.FriendlyGusCasparEvent;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;

public class FriendlyGusCaspar extends Thug {
	
	public FriendlyGusCaspar() {
		super(61, "\"Friendly\" Gus Caspar", 0, 0, 2);
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return (thePlayer.getGuns()>=1 &&  thePlayer.getCars()>=1);
	}
	
	@Override
	protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
		super.putInPlay(thePlayer, theGame);
		addPermanentEffect(new FriendlyGusCasparEvent(theGame, thePlayer, timingNumber, this));
	}
}