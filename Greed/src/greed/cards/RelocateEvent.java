package greed.cards;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;
import greed.eventypes.WhenPlayEvent;

public class RelocateEvent extends WhenPlayEvent{
	private int markers;
	
	public RelocateEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source, int markers) {
		super(theGame, owner, timingNumber, source);
		this.markers = markers;
	}
	
	@Override
	public void execute(GreedGame theGame, GreedCard theCard) {
		if (theCard instanceof Holding) {
			((Holding) theCard).changeMarkers(markers, "");
		}
	}
	
	@Override
	public void remove(GreedGame theGame) {
		super.remove(theGame);
		discardActionAfterNextTurnevent(theGame);
	}
	
}