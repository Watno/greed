package greed.game.cards.effects;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;
import greed.game.eventtypes.WhenPlayEvent;

public class ZoningOfficeWhenPlayEvent extends WhenPlayEvent{
	public ZoningOfficeWhenPlayEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source) {
		super(theGame, owner, timingNumber, source);
	}
	
	@Override
	public void execute(GreedGame theGame, GreedCard theCard) {
		if (theCard instanceof Holding && theCard!=source) {
			owner.gainCash(5000, this);
		}
	}
}