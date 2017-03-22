package greed.eventypes;

import java.util.Collections;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;

public abstract class EndOfGameEvent extends TriggeredEvent {
	protected GreedPlayer profiteer;
	protected GreedGame theGame;
	
	public EndOfGameEvent(GreedGame theGame, GreedPlayer profiteer, int timingNumber, GreedCard source){
		super(theGame.getEndOfGameEvents(), timingNumber, source);
		this.profiteer = profiteer;
		this.theGame = theGame;
	}
	
	@Override 
	public void activate() {
		theGame.getEndOfGameEvents().add(this);
		Collections.sort(theGame.getEndOfGameEvents());	
	}
	
	public void execute(GreedGame theGame) {
		
	}
}
