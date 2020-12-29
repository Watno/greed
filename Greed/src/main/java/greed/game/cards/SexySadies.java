package greed.game.cards;

import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;
import greed.game.cards.effects.SexySadiesEvent;

public class SexySadies extends Holding {
	
	public SexySadies() {
		super(49, "Sexy Sadie's", 0, 1, 0);
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return thePlayer.getCars()>=1;
	}
	
	@Override
	protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
		super.putInPlay(thePlayer, theGame);
		addPermanentEffect(new SexySadiesEvent(theGame, thePlayer, this, timingNumber));
	}
}