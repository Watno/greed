package greed.cards.effects;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.eventypes.EachTurnEvent;

public class DollsOnCallEvent extends EachTurnEvent {
	public DollsOnCallEvent(GreedGame theGame, GreedPlayer profiteer,  int timingNumber, GreedCard source) {
		super(theGame, profiteer, timingNumber, source);
	}

	@Override
	public void execute(GreedGame theGame) {
		int mostHoldings = 0;
		boolean tied = false;
		GreedPlayer ownerOfMostHoldings = null;
		for (GreedPlayer aPlayer : theGame.getPlayers()) {
			int aPlayersNumberOfHoldings = aPlayer.getNumberOfHoldings();
			if (aPlayersNumberOfHoldings == mostHoldings) {
				tied = true;
			}
			if (aPlayersNumberOfHoldings > mostHoldings) {
				mostHoldings = aPlayersNumberOfHoldings;
				tied = false;
				ownerOfMostHoldings = aPlayer;
			}
		}
		if (!tied) {
			ownerOfMostHoldings.gainCash(5000, " due to " + source.getName() + "'s effect");
		}
		
	}
}
