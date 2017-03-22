package greed.cards;

import greed.Action;
import greed.GreedGame;
import greed.GreedPlayer;

public class Sting extends Action {

	public Sting() {
		super(33, "Sting!");
	}
	
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		thePlayer.gainCash(thePlayer.getCars()*10000, "");
		thePlayer.gainCash(thePlayer.getHearts()*10000, "");	
	}
	
}