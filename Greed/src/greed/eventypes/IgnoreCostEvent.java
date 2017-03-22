package greed.eventypes;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;

public class IgnoreCostEvent extends TriggeredEvent {
	GreedPlayer owner;
	
	public IgnoreCostEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source){
		super(owner.getIgnoreCostEvents(), timingNumber, source);
		this.owner = owner;
	}
	
	@Override 
	public void activate() {
		owner.getIgnoreCostEvents().add(this);
	}
}
