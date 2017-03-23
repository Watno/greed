package greed.cards.effects;

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
			if (theCard.getLocation()==owner.getActions()) {
				owner.getHand().add(theCard);
				theCard.setLocation(owner.getHand());
				owner.getActions().remove(theCard);
			}
		}
	}
}