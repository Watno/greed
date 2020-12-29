package greed.game.cards.effects;

import greed.game.*;
import greed.game.eventtypes.AfterPlayEvent;

public class SandysSnookerNSchnapsEvent extends AfterPlayEvent{
	public SandysSnookerNSchnapsEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source) {
		super(theGame, owner, timingNumber, source);
	}
	
	@Override
	public void execute(GreedGame theGame, GreedCard theCard) {
		if (theCard instanceof Action) {
			if (source instanceof Holding) {
				((Holding) source).changeMarkers(1, this);
			}
		}
	}
}
