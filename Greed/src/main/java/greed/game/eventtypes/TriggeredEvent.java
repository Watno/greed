package greed.game.eventtypes;

import greed.game.*;

import java.util.ArrayList;

public abstract class TriggeredEvent implements Comparable<TriggeredEvent>, Cloneable, Reason {
	private final ArrayList<? extends TriggeredEvent> triggeringList;
	private final int timingNumber;
	protected GreedCard source;
	private final GreedPlayer cardOwner;

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
