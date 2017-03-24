package greed.game.cards;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;
import greed.game.cards.effects.LoanSharkEvent;

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
	public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
		thePlayer.gainCash(20000, executingCard);
	}
	
}
