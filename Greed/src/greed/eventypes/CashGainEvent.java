package greed.eventypes;

import java.util.Collections;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.eventypes.TriggeredEvent;

public abstract class CashGainEvent extends TriggeredEvent {
	protected GreedPlayer owner;
	
	public CashGainEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source){
		super(owner.getCashGainEvents(), timingNumber, source);
		this.owner = owner;
	}
	
	@Override 
	public void activate() {
		owner.getCashGainEvents().add(this);
		Collections.sort(owner.getCashGainEvents());	
	}
	
	public void execute(int amount) {
		
	}
}
