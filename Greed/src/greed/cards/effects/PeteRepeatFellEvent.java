package greed.cards.effects;

import greed.Action;
import greed.GreedCard;
import greed.GreedGame;
import greed.GreedPlayer;
import greed.eventypes.AfterPlayEvent;

public class PeteRepeatFellEvent extends AfterPlayEvent{
	
	public PeteRepeatFellEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source) {
		super(theGame, owner, timingNumber, source);
	}
	
	@Override
	public void execute(GreedGame theGame, GreedCard theCard) {
		if (theCard instanceof Action) {
			if (theCard.getLocation()==owner.getActions()) {
				owner.getHand().add(theCard);
				theCard.setLocation(owner.getHand());
				owner.getActions().remove(theCard);
			}
		}
	}
}