package greed.cards.effects;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.eventypes.EachTurnEvent;

public class NataschaTheSquirrelRubinEvent extends EachTurnEvent {
	
	public NataschaTheSquirrelRubinEvent(GreedGame theGame, GreedPlayer profiteer,  int timingNumber, GreedCard source) {
		super(theGame, profiteer, timingNumber, source);
	}

	@Override
	public void execute(GreedGame theGame) {
		profiteer.gainCash(5000, " due to " + source.getName() + "'s effect");
	}
}