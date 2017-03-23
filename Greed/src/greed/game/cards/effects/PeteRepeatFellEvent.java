package greed.game.cards.effects;

import greed.game.Action;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.eventtypes.AfterPlayEvent;

public class PeteRepeatFellEvent extends AfterPlayEvent{
	
	public PeteRepeatFellEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source) {
		super(theGame, owner, timingNumber, source);
	}
	
	@Override
	public void execute(GreedGame theGame, GreedCard theCard) {
		if (theCard instanceof Action) {
			theCard.putBackInHand(owner, theGame, this, owner.getActions());
		}
	}
}