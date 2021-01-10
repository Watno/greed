package greed.game.eventtypes;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

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
