package greed.game.cards;

import greed.cards.effects.ZoningOfficeCostModifyEvent;
import greed.cards.effects.ZoningOfficeWhenPlayEvent;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;

public class ZoningOffice extends Holding {
	
	public ZoningOffice() {
		super(52, "Zoning Office", 0, 0, 0);
	}
	
	
	@Override
	protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
		super.putInPlay(thePlayer, theGame);
		addPermanentEffect(new ZoningOfficeCostModifyEvent(theGame, thePlayer, timingNumber, this));
		addPermanentEffect(new ZoningOfficeWhenPlayEvent(theGame, thePlayer, timingNumber, this));
	}
}