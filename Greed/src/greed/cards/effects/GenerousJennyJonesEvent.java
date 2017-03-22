package greed.cards.effects;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.eventypes.EndOfGameEvent;

public class GenerousJennyJonesEvent extends EndOfGameEvent{
	public GenerousJennyJonesEvent(GreedGame theGame, GreedPlayer profiteer, int timingNumber, GreedCard source) {
		super(theGame, profiteer, timingNumber, source);
	}
	
	@Override
	public void execute(GreedGame theGame) {
		profiteer.changeCash(-25000, " due to " + source.getName() + "'s effect");
	}
}
