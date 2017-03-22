package greed.cards.effects;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;
import greed.eventypes.WhenPlayEvent;

public class ZoningOfficeWhenPlayEvent extends WhenPlayEvent{
	public ZoningOfficeWhenPlayEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source) {
		super(theGame, owner, timingNumber, source);
	}
	
	@Override
	public void execute(GreedGame theGame, GreedCard theCard) {
		if (theCard instanceof Holding && theCard!=source) {
			owner.gainCash(5000, " due to " + source.getName() + "'s effect");
		}
	}
}