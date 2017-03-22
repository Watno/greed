package greed.cards.effects;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.eventypes.EachTurnEvent;

public class BiscuitsOMalleyEvent extends EachTurnEvent {
	
	public BiscuitsOMalleyEvent(GreedGame theGame, GreedPlayer profiteer, int timingNumber, GreedCard source) {
		super(theGame, profiteer, timingNumber, source);
	}

	@Override
	public void execute(GreedGame theGame) {
		if (profiteer.getCash()==0) {
			profiteer.gainCash(10000, " due to " + source.getName() + "'s effect");
		}
	}
}