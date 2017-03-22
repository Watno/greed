package greed.eventypes;

import java.util.Collections;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;

public abstract class EndOfTurnEvent extends TriggeredEvent {
	protected GreedPlayer initiator;
	protected GreedGame theGame;
	
	public EndOfTurnEvent(GreedGame theGame, GreedPlayer initiator, int timingNumber, GreedCard source){
		super(theGame.getEndOfTurnEvents(), timingNumber, source);
		this.theGame = theGame;
		this.initiator = initiator;
	}
	
	@Override 
	public void activate() {
		theGame.getEndOfTurnEvents().add(this);
		Collections.sort(theGame.getEndOfTurnEvents());	
	}
	
	public void execute(GreedGame theGame) {
		
	}
}
