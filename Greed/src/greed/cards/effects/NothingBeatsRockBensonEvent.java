package greed.cards.effects;

import greed.Action;
import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.eventypes.AfterPlayEvent;

public class NothingBeatsRockBensonEvent extends AfterPlayEvent{
	public NothingBeatsRockBensonEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source) {
		super(theGame, owner, timingNumber, source);
	}
	
	@Override
	public void execute(GreedGame theGame, GreedCard theCard) {
		if (theCard instanceof Action) {
			owner.gainCash(5000, " due to " + source.getName() + "'s effect");
		}
	}
}
