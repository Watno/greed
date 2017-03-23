package greed.game.cards;

import greed.cards.effects.LoanSharkEvent;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;

public class LoanShark extends Holding {
	
	public LoanShark() {
		super(44, "Loan Shark", 0, 0, 0);
	}
	
	
	@Override
	protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
		super.putInPlay(thePlayer, theGame);
		addPermanentEffect(new LoanSharkEvent(theGame, thePlayer, timingNumber, this));
	}
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		thePlayer.gainCash(20000, "");
	}
	
}
