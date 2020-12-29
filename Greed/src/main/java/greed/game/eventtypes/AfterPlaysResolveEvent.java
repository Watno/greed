package greed.game.eventtypes;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

import java.util.Collections;

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
