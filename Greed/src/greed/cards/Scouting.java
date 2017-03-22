package greed.cards;

import greed.Action;
import greed.GreedGame;
import greed.GreedPlayer;

public class Scouting extends Action {

	public Scouting() {
		super(74, "Scouting!");
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		if(thePlayer.getCars()>=1) {
			thePlayer.gainCash(10000, "");
		}
		if(thePlayer.getCars()>=1) {
			for(GreedPlayer otherPlayer : theGame.getPlayers()) {
				if(otherPlayer!=thePlayer) {
					otherPlayer.changeCash(-10000, "");
				}
			}
		}
		if(thePlayer.getKeys()>=1) {
			thePlayer.drawCard(theGame);
		}
	}
}
