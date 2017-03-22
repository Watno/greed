package greed.cards;

import greed.Action;
import greed.GreedGame;
import greed.GreedPlayer;

public class Smuggling extends Action {

	public Smuggling() {
		super(32, "Smuggling!");
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return (thePlayer.getCars()>=1 && thePlayer.getBottles()>=1);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		thePlayer.gainCash(25000, "");
	}
}