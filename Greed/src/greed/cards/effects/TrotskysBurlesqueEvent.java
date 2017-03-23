package greed.cards.effects;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.eventtypes.EachTurnEvent;

public class TrotskysBurlesqueEvent extends EachTurnEvent {
	public TrotskysBurlesqueEvent(GreedGame theGame, GreedPlayer profiteer,  int timingNumber, GreedCard source) {
		super(theGame, profiteer, timingNumber, source);
	}

	@Override
	public void execute(GreedGame theGame) {
		int mostHearts = 0;
		boolean tied = false;
		GreedPlayer ownerOfMostHearts = null;
		for (GreedPlayer aPlayer : theGame.getPlayers()) {
			int aPlayersNumberOfHearts = aPlayer.getHearts();
			if (aPlayersNumberOfHearts == mostHearts) {
				tied = true;
			}
			if (aPlayersNumberOfHearts > mostHearts) {
				mostHearts = aPlayersNumberOfHearts;
				tied = false;
				ownerOfMostHearts = aPlayer;
			}
		}
		if (!tied) {
			ownerOfMostHearts.gainCash(5000, " due to " + source.getName() + "'s effect");
		}
		
	}
}
