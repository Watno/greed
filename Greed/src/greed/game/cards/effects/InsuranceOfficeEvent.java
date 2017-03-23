package greed.game.cards.effects;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;
import greed.game.Thug;
import greed.game.eventtypes.RemoveFromPlayEvent;

public class InsuranceOfficeEvent extends RemoveFromPlayEvent{
	
	public InsuranceOfficeEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source) {
		super(theGame, owner, timingNumber, source);
	}
	
	@Override
	public void execute(GreedGame theGame, GreedCard removedCard) {
		if (source instanceof Holding) {
			if (removedCard instanceof Thug || (removedCard instanceof Holding && removedCard!=source)) {
				((Holding) source).changeMarkers(2, this);
			}
		}
	}
}