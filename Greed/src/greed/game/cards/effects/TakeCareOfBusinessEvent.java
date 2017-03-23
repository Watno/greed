package greed.game.cards.effects;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.eventtypes.EndOfTurnEvent;

public class TakeCareOfBusinessEvent extends EndOfTurnEvent {
	
	public TakeCareOfBusinessEvent(GreedGame theGame, GreedPlayer initiator, int timingNumber, GreedCard source) {
		super(theGame, initiator, timingNumber, source);
	}

	@Override
	public void execute(GreedGame theGame) {
		for (GreedPlayer otherPlayer : theGame.getPlayers()) {
			if(otherPlayer != initiator) {
				otherPlayer.loseThug(this);
			}
		}
	}
	
	@Override
	public void remove(GreedGame theGame) {
		super.remove(theGame);
		discardActionAfterNextTurnevent(theGame);
	}
}