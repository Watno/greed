package greed.cards.effects;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.eventypes.EachTurnEvent;

public class JennysWaterfrontDiveEvent extends EachTurnEvent {
	
	public JennysWaterfrontDiveEvent(GreedGame theGame, GreedPlayer profiteer,  int timingNumber, GreedCard source) {
		super(theGame, profiteer, timingNumber, source);
	}

	@Override
	public void execute(GreedGame theGame) {
		boolean noBottlesOrHearts = true;
		for (GreedPlayer aPlayer : theGame.getPlayers()) {
			if(aPlayer.getBottles()>=1 || aPlayer.getHearts()>=1) {
				noBottlesOrHearts = false;
			}
		}
		if (noBottlesOrHearts) {
			profiteer.gainCash(10000, " due to " + source.getName() + "'s effect");
		}
	}
	
	
}