package greed.game.cards.effects;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;
import greed.game.eventtypes.WhenPlayEvent;

public class ShakedownEvent extends WhenPlayEvent{
	GreedPlayer profiteer;
	public ShakedownEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source, GreedPlayer profiteer) {
		super(theGame, owner, timingNumber, source);
		this.profiteer = profiteer;
	}
	
	@Override
	public void execute(GreedGame theGame, GreedCard theCard) {
		if (theCard instanceof Thug) {
			profiteer.gainCash(10000, this);
		}
	}
}