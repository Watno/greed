package greed.cards.effects;

import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;
import greed.eventypes.EndOfGameEvent;

public class BookieJointEvent extends EndOfGameEvent{
	public BookieJointEvent(GreedGame theGame, GreedPlayer profiteer, int timingNumber, GreedCard source) {
		super(theGame, profiteer, timingNumber, source);
	}
	
	@Override
	public void execute(GreedGame theGame) {
		if (source instanceof Holding) {
			profiteer.gainCash(((Holding) source).getMarkers()*5000, " due to " + source.getName() + "'s effect");
		}
	}
}