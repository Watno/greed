package greed.game.cards.effects;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.eventtypes.CashCostModifyEvent;

public class StingyStanMcDowellEvent extends CashCostModifyEvent {
	
	public StingyStanMcDowellEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source) {
		super(theGame, owner, timingNumber, source);
	}

	@Override
	public int execute(int amount, GreedCard paidCard) {
		return (Math.max(amount - 5000, 0)); 
	}
}
