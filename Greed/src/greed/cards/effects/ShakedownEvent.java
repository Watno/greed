package greed.cards.effects;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.Thug;
import greed.eventypes.WhenPlayEvent;

public class ShakedownEvent extends WhenPlayEvent{
	GreedPlayer profiteer;
	public ShakedownEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source, GreedPlayer profiteer) {
		super(theGame, owner, timingNumber, source);
		this.profiteer = profiteer;
	}
	
	@Override
	public void execute(GreedGame theGame, GreedCard theCard) {
		if (theCard instanceof Thug) {
			profiteer.gainCash(10000, " due to " + source.getName() + "'s effect");
		}
	}
}