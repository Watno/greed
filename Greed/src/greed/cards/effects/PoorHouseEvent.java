package greed.cards.effects;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.eventypes.EachTurnEvent;

public class PoorHouseEvent extends EachTurnEvent {
	public PoorHouseEvent(GreedGame theGame, GreedPlayer profiteer,  int timingNumber, GreedCard source) {
		super(theGame, profiteer, timingNumber, source);
	}

	@Override
	public void execute(GreedGame theGame) {
		if(profiteer.getNumberOfHoldings()<=2 && profiteer.getNumberOfThugs()<=2) {
			profiteer.gainCash(5000, " due to " + source.getName() + "'s effect");
		}
	}
}
