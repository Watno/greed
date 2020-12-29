package greed.game.eventtypes;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

import java.util.Collections;

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
