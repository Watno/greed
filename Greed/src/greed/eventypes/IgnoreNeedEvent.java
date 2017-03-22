package greed.eventypes;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;

public class IgnoreNeedEvent extends TriggeredEvent {
	GreedPlayer owner;
	
	public IgnoreNeedEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source){
		super(owner.getIgnoreNeedEvents(), timingNumber, source);
		this.owner = owner;
	}
	
	@Override 
	public void activate() {
		owner.getIgnoreNeedEvents().add(this);
	}
		
		
}
