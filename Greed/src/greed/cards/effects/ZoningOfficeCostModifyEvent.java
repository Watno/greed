package greed.cards.effects;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;
import greed.eventypes.CashCostModifyEvent;

public class ZoningOfficeCostModifyEvent extends CashCostModifyEvent {
	
	public ZoningOfficeCostModifyEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source) {
		super(theGame, owner, timingNumber, source);
	}

	@Override
	public int execute(int amount, GreedCard payedCard) {
		if (payedCard instanceof Holding) {
			return (Math.max(amount - 5000, 0)); 
		}
		return amount;
	}
}
