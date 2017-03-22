package greed.eventypes;

import java.util.ArrayList;

import greed.Action;
import greed.GreedCard;
import greed.GreedGame;

public abstract class TriggeredEvent implements Comparable<TriggeredEvent>, Cloneable {
	private ArrayList<? extends TriggeredEvent> triggeringList;
	private int timingNumber;
	protected GreedCard source;
	
	public TriggeredEvent(ArrayList<? extends TriggeredEvent> triggeringList, int timingNumber, GreedCard source) {
		this.triggeringList = triggeringList;
		this.timingNumber = timingNumber;
		this.source = source;
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
}
