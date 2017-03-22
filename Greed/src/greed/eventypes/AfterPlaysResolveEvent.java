package greed.eventypes;

import java.util.Collections;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;

public abstract class AfterPlaysResolveEvent extends TriggeredEvent {
	protected GreedPlayer profiteer;
	protected GreedGame theGame;
	
	public AfterPlaysResolveEvent(GreedGame theGame, GreedPlayer profiteer, int timingNumber, GreedCard source){
		super(theGame.getAfterPlaysResolveEvents(), timingNumber, source);
		this.theGame = theGame;
		this.profiteer = profiteer;
	}
	
	@Override 
	public void activate() {
		theGame.getAfterPlaysResolveEvents().add(this);
		Collections.sort(theGame.getAfterPlaysResolveEvents());	
	}
	
	public void execute(GreedGame theGame) {
		
	}
}
