package greed.game.cards;

import greed.game.Action;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

public class ProtectionRacket extends Action {

	public ProtectionRacket() {
		super(60, "Protection Racket!");
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
		int mostHoldings=0;
		for (GreedPlayer anyPlayer: theGame.getPlayers()) {
			mostHoldings=Math.max(mostHoldings, anyPlayer.getNumberOfHoldings());
		}
		thePlayer.gainCash(mostHoldings*5000, executingCard);
	}
}
