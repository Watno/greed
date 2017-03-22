package greed.cards.effects;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;
import greed.eventypes.WhenPlayEvent;

public class ComplexSchemeEvent extends WhenPlayEvent{
	
	public ComplexSchemeEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source) {
		super(theGame, owner, timingNumber, source);
	}
	
	@Override
	public void execute(GreedGame theGame, GreedCard theCard) {
		if (theCard instanceof Holding) {
			((Holding) theCard).changeMarkers(3, " due to " + source.getName() + "'s effect");
		}
	}
	
	@Override
	public void remove(GreedGame theGame) {
		super.remove(theGame);
		discardActionAfterNextTurnevent(theGame);
	}
	
}