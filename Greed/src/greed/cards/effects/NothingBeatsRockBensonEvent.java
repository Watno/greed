package greed.cards.effects;

import greed.game.Action;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.eventtypes.AfterPlayEvent;

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
