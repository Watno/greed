package greed.cards.effects;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.eventypes.EndOfTurnEvent;

public class VandalismEvent extends EndOfTurnEvent {
	
	public VandalismEvent(GreedGame theGame, GreedPlayer initiator, int timingNumber, GreedCard source) {
		super(theGame, initiator, timingNumber, source);
	}

	@Override
	public void execute(GreedGame theGame) {
		for (GreedPlayer otherPlayer : theGame.getPlayers()) {
			if(otherPlayer != initiator) {
				otherPlayer.loseHolding(theGame, " due to " + source.getName() + "'s effect");
			}
		}
	}
	
	@Override
	public void remove(GreedGame theGame) {
		super.remove(theGame);
		discardActionAfterNextTurnevent(theGame);
	}
}
