package greed.game.eventtypes;

import java.util.Collections;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.eventtypes.TriggeredEvent;

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
