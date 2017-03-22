package greed.cards.effects;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.eventypes.CashCostModifyEvent;

public class StingyStanMcDowellEvent extends CashCostModifyEvent {
	
	public StingyStanMcDowellEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source) {
		super(theGame, owner, timingNumber, source);
	}

	@Override
	public int execute(int amount, GreedCard payedCard) {
		return (Math.max(amount - 5000, 0)); 
	}
}
