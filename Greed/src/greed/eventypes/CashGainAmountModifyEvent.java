package greed.eventypes;

import java.util.Collections;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;

public abstract class CashGainAmountModifyEvent extends TriggeredEvent {
	protected GreedPlayer owner;
	
	public CashGainAmountModifyEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source){
		super(owner.getCashGainAmountModifyEvents(), timingNumber, source);
		this.owner = owner;
	}
	
	@Override 
	public void activate() {
		owner.getCashGainAmountModifyEvents().add(this);
		Collections.sort(owner.getCashGainAmountModifyEvents());	
	}
	
	public int execute(int amount) {
		return amount;
	}
}
