package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;
import greed.cards.effects.InsuranceOfficeEvent;

public class InsuranceOffice extends Holding {	
	public InsuranceOffice() {
		super(45, "Insurance Office", 0, 0, 0);
	}
	
	@Override
	public int changeMarkers(int amount, String reason) {
		return super.changeMarkers(Math.max(0, amount), reason);//can't lose markers
	}
	
	@Override
	protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
		super.putInPlay(thePlayer, theGame);
		addPermanentEffect(new InsuranceOfficeEvent(theGame, thePlayer, timingNumber, this));
	}
}