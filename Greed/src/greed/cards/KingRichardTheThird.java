package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Thug;

public class KingRichardTheThird extends Thug {
	
	public KingRichardTheThird() {
		super(21, "\"King\" Richard the Third", 1, 1, 1);
	}
	
	@Override
	public boolean payCost(GreedPlayer thePlayer, GreedGame theGame) {
		if (thePlayer.payThug(theGame, this)!=null) {
			return true;
		}
		if (thePlayer.payHolding(theGame, this)!=null) {
			return true;
		}
		return thePlayer.payCashCost(10000, this);
	}
	
	@Override
	public boolean isPlayable(GreedPlayer thePlayer, GreedGame theGame) {
		super.isPlayable(thePlayer, theGame);
		return (thePlayer.getNumberOfThugs()>=1 || thePlayer.getNumberOfHoldings()>=1 || thePlayer.getCash()>=10000);
	}
}