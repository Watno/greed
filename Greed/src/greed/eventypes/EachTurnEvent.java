package greed.eventypes;

import java.util.Collections;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;

public abstract class EachTurnEvent extends TriggeredEvent {
	protected GreedPlayer profiteer;
	protected GreedGame theGame;
	
	public EachTurnEvent(GreedGame theGame, GreedPlayer profiteer, int timingNumber, GreedCard source){
		super(theGame.getEachTurnEvents(), timingNumber, source);
		this.theGame = theGame;
		this.profiteer = profiteer;
	}
	
	@Override 
	public void activate() {
		theGame.getEachTurnEvents().add(this);
		Collections.sort(theGame.getEachTurnEvents());	
	}
	
	public void execute(GreedGame theGame) {
		
	}
}
