package greed.game.cards.effects;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;
import greed.game.eventtypes.WhenPlayEvent;

public class FriendlyGusCasparEvent extends WhenPlayEvent{
	public FriendlyGusCasparEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source) {
		super(theGame, owner, timingNumber, source);
	}
	
	@Override
	public void execute(GreedGame theGame, GreedCard theCard) {
		if (theCard instanceof Thug && theCard!=source) {
			owner.gainCash(15000, this);
		}
	}
}