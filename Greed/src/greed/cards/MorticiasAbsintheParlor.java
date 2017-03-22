package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;

public class MorticiasAbsintheParlor extends Holding {
	
	public MorticiasAbsintheParlor() {
		super(14, "Morticia's Absinthe Parlor", 1, 1, 0);
	}
	
	@Override
	public boolean payCost(GreedPlayer thePlayer, GreedGame theGame) {
		return thePlayer.payCashCost(10000, this);
	}
	
	@Override
	public boolean isPlayable(GreedPlayer thePlayer, GreedGame theGame) {
		super.isPlayable(thePlayer, theGame);
		return thePlayer.getCash()>=10000;
	}
}
