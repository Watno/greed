package greed.game.eventtypes;

import java.util.Collections;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

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
