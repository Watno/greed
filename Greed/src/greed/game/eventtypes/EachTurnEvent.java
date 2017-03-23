package greed.game.eventtypes;

import java.util.Collections;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

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
