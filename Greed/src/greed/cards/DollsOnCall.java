package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;
import greed.cards.effects.DollsOnCallEvent;

public class DollsOnCall extends Holding {
	
	public DollsOnCall() {
		super(10, "Dolls on Call", 0, 1, 0);
	}
	
	@Override
	public boolean payCost(GreedPlayer thePlayer, GreedGame theGame) {
		return thePlayer.payCashCost(15000, this);
	}
	
	@Override
	public boolean isPlayable(GreedPlayer thePlayer, GreedGame theGame) {
		super.isPlayable(thePlayer, theGame);
		return thePlayer.getCash()>=15000;
	}
	
	@Override
	protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
		super.putInPlay(thePlayer, theGame);
		addPermanentEffect(new DollsOnCallEvent(theGame, thePlayer, timingNumber, this));
	}
}