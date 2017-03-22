package greed.cards.effects;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.eventypes.CashGainAmountModifyEvent;

public class MasterPlanEvent extends CashGainAmountModifyEvent{
	public MasterPlanEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source) {
		super(theGame, owner, timingNumber, source);
	}
	
	@Override
	public int execute(int amount) {
		return amount*2;
	}
	
	@Override
	public void remove(GreedGame theGame) {
		super.remove(theGame);
		discardActionAfterNextTurnevent(theGame);
	}
}