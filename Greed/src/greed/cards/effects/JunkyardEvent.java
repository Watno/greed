package greed.cards.effects;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;
import greed.eventypes.EndOfGameEvent;

public class JunkyardEvent extends EndOfGameEvent{
	public JunkyardEvent(GreedGame theGame, GreedPlayer profiteer, int timingNumber, GreedCard source) {
		super(theGame, profiteer, timingNumber, source);
	}
	
	@Override
	public void execute(GreedGame theGame) {
		if (source instanceof Holding) {
			Holding sourceAsHolding  = (Holding) source;
			sourceAsHolding.placeMarkers(profiteer);
		}
	}
}
