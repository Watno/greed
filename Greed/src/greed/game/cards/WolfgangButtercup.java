package greed.game.cards;

import greed.cards.effects.WolfgangButtercupEvent;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;

public class WolfgangButtercup extends Thug {
	
	public WolfgangButtercup() {
		super(42, "Wolfgang Buttercup", 2, 0, 0);
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return thePlayer.getNumberOfThugs()>=2;
	}
	
	@Override
	protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
		super.putInPlay(thePlayer, theGame);
		addPermanentEffect(new WolfgangButtercupEvent(theGame, thePlayer, timingNumber, this));
	}
}