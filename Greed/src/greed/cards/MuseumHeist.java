package greed.cards;

import greed.Action;
import greed.GreedGame;
import greed.GreedPlayer;

public class MuseumHeist extends Action {

	public MuseumHeist() {
		super(30, "Museum heist!");
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return (thePlayer.getGuns()>=1 && thePlayer.getCars()>=1 && thePlayer.getKeys()>=1);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		thePlayer.gainCash(25000, "");
	}
}
