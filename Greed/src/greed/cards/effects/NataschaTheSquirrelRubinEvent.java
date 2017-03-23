package greed.cards.effects;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.eventtypes.EachTurnEvent;

public class NataschaTheSquirrelRubinEvent extends EachTurnEvent {
	
	public NataschaTheSquirrelRubinEvent(GreedGame theGame, GreedPlayer profiteer,  int timingNumber, GreedCard source) {
		super(theGame, profiteer, timingNumber, source);
	}

	@Override
	public void execute(GreedGame theGame) {
		profiteer.gainCash(5000, " due to " + source.getName() + "'s effect");
	}
}