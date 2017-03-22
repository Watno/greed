package greed.cards.effects;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.eventypes.WhenPlayEvent;
import greed.Action;

public class InformEvent extends WhenPlayEvent{
	GreedPlayer profiteer;
	public InformEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source, GreedPlayer profiteer) {
		super(theGame, owner, timingNumber, source);
		this.profiteer = profiteer;
	}
	
	@Override
	public void execute(GreedGame theGame, GreedCard theCard) {
		if (theCard instanceof Action) {
			profiteer.gainCash(10000, " due to " + source.getName() + "'s effect");
		}
	}
}