package greed.cards.effects;

import greed.Action;
import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;
import greed.eventypes.AfterPlayEvent;

public class SandysSnookerNSchnapsEvent extends AfterPlayEvent{
	public SandysSnookerNSchnapsEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source) {
		super(theGame, owner, timingNumber, source);
	}
	
	@Override
	public void execute(GreedGame theGame, GreedCard theCard) {
		if (theCard instanceof Action) {
			if (source instanceof Holding) {
				((Holding) source).changeMarkers(1, " due to " + source.getName() + "'s effect");
			}
		}
	}
}
