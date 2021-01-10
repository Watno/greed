package greed.game.eventtypes;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

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
