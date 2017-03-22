package greed.eventypes;

import java.util.Collections;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;

public class RemoveFromPlayEvent extends TriggeredEvent {
	protected GreedPlayer owner;
	
	public RemoveFromPlayEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source){
		super(owner.getRemoveFromPlayEvents(), timingNumber, source);
		this.owner = owner;
	}
	
	@Override 
	public void activate() {
		owner.getRemoveFromPlayEvents().add(this);
		Collections.sort(owner.getRemoveFromPlayEvents());	
	}
	
	public void execute(GreedGame theGame, GreedCard removedCard) {
		
	}
}