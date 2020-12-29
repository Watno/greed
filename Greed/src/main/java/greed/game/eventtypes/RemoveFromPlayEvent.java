package greed.game.eventtypes;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

import java.util.Collections;

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