package greed.game.cards.effects;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;
import greed.game.eventtypes.EndOfGameEvent;

public class JunkyardEvent extends EndOfGameEvent{
	public JunkyardEvent(GreedGame theGame, GreedPlayer profiteer, int timingNumber, GreedCard source) {
		super(theGame, profiteer, timingNumber, source);
	}
	
	@Override
	public void execute(GreedGame theGame) {
		if (source instanceof Holding) {
			Holding sourceAsHolding  = (Holding) source;
			sourceAsHolding.placeMarkersForReal(profiteer);
		}
	}
}
