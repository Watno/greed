package greed.cards;

import greed.Action;
import greed.GreedGame;
import greed.GreedPlayer;

public class CircusOfCrime extends Action {

	public CircusOfCrime() {
		super(26, "Circus of crime!");
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return (thePlayer.getNumberOfHoldings()>=2);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		thePlayer.gainCash(thePlayer.getNumberOfThugs()*10000, "");
	}
}