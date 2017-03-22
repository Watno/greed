package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Thug;
import greed.cards.effects.FriendlyGusCasparEvent;

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