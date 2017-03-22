package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;

public class MassageParlor extends Holding {
	
	public MassageParlor() {
		super(48, "Massage Parlor", 0,1,1);
	}

	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return (thePlayer.getCars()>=2);
	}
}
