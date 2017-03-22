package greed.cards.effects;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.eventypes.EndOfTurnEvent;

public class InsuranceScamEvent extends EndOfTurnEvent {
	
	public InsuranceScamEvent(GreedGame theGame, GreedPlayer initiator, int timingNumber, GreedCard source) {
		super(theGame, initiator, timingNumber, source);
	}

	@Override
	public void execute(GreedGame theGame) {
		for (GreedPlayer thePlayer : theGame.getPlayers()) {
			thePlayer.loseHolding(theGame, " due to " + source.getName() + "'s effect");
		}
	}
	
	@Override
	public void remove(GreedGame theGame) {
		super.remove(theGame);
		discardActionAfterNextTurnevent(theGame);
	}
	
}
