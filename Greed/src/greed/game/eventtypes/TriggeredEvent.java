package greed.game.eventtypes;

import java.util.ArrayList;

import greed.game.Action;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Reason;

public abstract class TriggeredEvent implements Comparable<TriggeredEvent>, Cloneable, Reason {
	private ArrayList<? extends TriggeredEvent> triggeringList;
	private int timingNumber;
	protected GreedCard source;
	private GreedPlayer cardOwner;
	
	public TriggeredEvent(ArrayList<? extends TriggeredEvent> triggeringList, int timingNumber, GreedCard source) {
		this.triggeringList = triggeringList;
		this.timingNumber = timingNumber;
		this.source = source;
		this.cardOwner = source.getOwner();
	}
	
	public void activate() {
		
	}
	
	public void remove(GreedGame theGame) {
		triggeringList.remove(this);
	}
	
	@Override
	public int compareTo(TriggeredEvent otherEvent) {
		return timingNumber - otherEvent.getTimingNumber();
	}

	public int getTimingNumber() {
		return timingNumber;
	}
	
	public void discardActionAfterNextTurnevent(GreedGame theGame) {
		if (source instanceof Action) {
			((Action) source).discardAfterNextTurnEvent(theGame);
		}
	}
	
	@Override
	public Object clone() {
	    try{  
	        return super.clone();  
	    }catch(Exception e){ 
	        return null; 
	    }
	}

	public void setSource(GreedCard source) {
		this.source = source;
	}
	
	public GreedCard getSource() {
		return source;
	}
	
	public GreedPlayer getCardOwner() {
		return cardOwner;
	}
}
