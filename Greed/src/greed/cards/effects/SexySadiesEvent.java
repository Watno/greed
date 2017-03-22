package greed.cards.effects;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;
import greed.eventypes.EachTurnEvent;

public class SexySadiesEvent extends EachTurnEvent {
	
	public SexySadiesEvent(GreedGame theGame, GreedPlayer profiteer, GreedCard source,  int timingNumber) {
		super(theGame, profiteer, timingNumber, source);
	}

	@Override
	public void execute(GreedGame theGame) {
		if (source instanceof Holding) {
			if(((Holding) source).getMarkers()>=3) {
				profiteer.gainCash(5000, " due to " + source.getName() + "'s effect");
			}
		}
	}
}