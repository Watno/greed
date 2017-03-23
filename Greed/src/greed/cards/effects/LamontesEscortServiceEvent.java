package greed.cards.effects;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.eventtypes.EndOfGameEvent;

public class LamontesEscortServiceEvent extends EndOfGameEvent{
	public LamontesEscortServiceEvent(GreedGame theGame, GreedPlayer profiteer, int timingNumber, GreedCard source) {
		super(theGame, profiteer, timingNumber, source);
	}
	
	@Override
	public void execute(GreedGame theGame) {
		int numberOfThugs = profiteer.getNumberOfThugs();
		boolean fewestThugs = true;
		for (GreedPlayer otherPlayer : theGame.getPlayers()) {
			if (!otherPlayer.equals(profiteer)) {
				if (otherPlayer.getNumberOfThugs()<=numberOfThugs) {
					fewestThugs = false;
				}
			}
		}
		if (fewestThugs) {
			profiteer.gainCash(20000, " due to " + source.getName() + "'s effect");
		}
	}
}
