package greed.eventypes;

import java.util.Collections;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;

public  abstract class AfterPlayEvent extends TriggeredEvent {
	protected GreedPlayer owner;
	
	public AfterPlayEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source){
		super(owner.getAfterPlayEvents(), timingNumber, source);
		this.owner = owner;
	}
	
	@Override 
	public void activate() {
		owner.getAfterPlayEvents().add(this);
		Collections.sort(owner.getAfterPlayEvents());	
	}
	
	public void execute(GreedGame theGame, GreedCard theCard) {
		
	}
}