package greed.eventypes;

import java.util.Collections;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;

public abstract class CashCostModifyEvent extends TriggeredEvent {
	protected GreedPlayer owner;
	
	public CashCostModifyEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source){
		super(owner.getCashCostModifyEvents(), timingNumber, source);
		this.owner = owner;
	}
	
	@Override 
	public void activate() {
		owner.getCashCostModifyEvents().add(this);
		Collections.sort(owner.getCashCostModifyEvents());	
	}
	
	public int execute(int amount, GreedCard payedCard) {
		return amount;
	}
}
