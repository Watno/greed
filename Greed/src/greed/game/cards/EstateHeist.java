package greed.game.cards;

import greed.game.Action;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

public class EstateHeist extends Action {

	public EstateHeist() {
		super(28, "Estate heist!");
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		if(thePlayer.getGuns()>=1) {
			thePlayer.gainCash(10000, "");
		}
		if(thePlayer.getCars()>=1) {
			thePlayer.gainCash(10000, "");
		}
		if(thePlayer.getKeys()>=1) {
			thePlayer.gainCash(10000, "");
		}
	}
}
