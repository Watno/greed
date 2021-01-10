package greed.game.eventtypes;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

import java.util.Collections;

public abstract class EndOfTurnEvent extends TriggeredEvent {
    protected final GreedPlayer initiator;
    protected final GreedGame theGame;

    public EndOfTurnEvent(GreedGame theGame, GreedPlayer initiator, int timingNumber, GreedCard source) {
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
