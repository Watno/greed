package greed.game.cards;

import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;
import greed.game.cards.effects.ZoningOfficeCostModifyEvent;
import greed.game.cards.effects.ZoningOfficeWhenPlayEvent;

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