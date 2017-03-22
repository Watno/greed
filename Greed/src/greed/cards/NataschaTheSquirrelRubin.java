package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;
import greed.cards.effects.NataschaTheSquirrelRubinEvent;

public class NataschaTheSquirrelRubin extends Holding {
	
	public NataschaTheSquirrelRubin() {
		super(69, "Natascha \"The Squirrel\" Rubin", 0, 0, 0);
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return thePlayer.getNumberOfHoldings()>=2;
	}
	
	@Override
	protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
		super.putInPlay(thePlayer, theGame);
		addPermanentEffect(new NataschaTheSquirrelRubinEvent(theGame, thePlayer, timingNumber, this));
	}
}