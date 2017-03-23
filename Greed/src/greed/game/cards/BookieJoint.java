package greed.game.cards;

import greed.cards.effects.BookieJointEvent;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;

public class BookieJoint extends Holding {
	
	public BookieJoint() {
		super(7, "Bookie Joint", 0, 0, 1);
	}
	
	@Override
	public boolean payCost(GreedPlayer thePlayer, GreedGame theGame) {
		return thePlayer.payCashCost(10000, this);
	}
	
	@Override
	public boolean isPlayable(GreedPlayer thePlayer, GreedGame theGame) {
		super.isPlayable(thePlayer, theGame);
		return thePlayer.getCash()>=10000;
	}
	
	@Override
	protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
		super.putInPlay(thePlayer, theGame);
		addPermanentEffect(new BookieJointEvent(theGame, thePlayer, timingNumber, this));
	}
}
