package greed.game.cards.effects;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.eventtypes.EachTurnEvent;

public class BiscuitsOMalleyEvent extends EachTurnEvent {
	
	public BiscuitsOMalleyEvent(GreedGame theGame, GreedPlayer profiteer, int timingNumber, GreedCard source) {
		super(theGame, profiteer, timingNumber, source);
	}

	@Override
	public void execute(GreedGame theGame) {
		if (profiteer.getCash()==0) {
			profiteer.gainCash(10000, this);
		}
	}
}