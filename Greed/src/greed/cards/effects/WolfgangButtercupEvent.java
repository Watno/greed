package greed.cards.effects;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;
import greed.eventypes.WhenPlayEvent;

public class WolfgangButtercupEvent extends WhenPlayEvent{
	
	public WolfgangButtercupEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source) {
		super(theGame, owner, timingNumber, source);
	}
	
	@Override
	public void execute(GreedGame theGame, GreedCard theCard) {
		if (theCard instanceof Holding) {
			((Holding) theCard).changeMarkers(1, " due to " + source.getName() + "'s effect");
		}
	}
}