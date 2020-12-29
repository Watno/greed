package greed.game.eventtypes;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

import java.util.Collections;

public abstract class WhenPlayEvent extends TriggeredEvent {
	protected GreedPlayer owner;
	
	public WhenPlayEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source){
		super(owner.getWhenPlayEvents(), timingNumber, source);
		this.owner = owner;
	}
	
	@Override 
	public void activate() {
		owner.getWhenPlayEvents().add(this);
		Collections.sort(owner.getWhenPlayEvents());	
	}
	
	public void execute(GreedGame theGame, GreedCard theCard) {
		
	}
}