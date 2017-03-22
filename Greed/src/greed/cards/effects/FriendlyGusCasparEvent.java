package greed.cards.effects;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.Thug;
import greed.eventypes.WhenPlayEvent;

public class FriendlyGusCasparEvent extends WhenPlayEvent{
	public FriendlyGusCasparEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source) {
		super(theGame, owner, timingNumber, source);
	}
	
	@Override
	public void execute(GreedGame theGame, GreedCard theCard) {
		if (theCard instanceof Thug && theCard!=source) {
			owner.gainCash(15000, " due to " + source.getName() + "'s effect");
		}
	}
}