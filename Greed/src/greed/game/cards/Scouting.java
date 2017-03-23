package greed.game.cards;

import greed.game.Action;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

public class Scouting extends Action {

	public Scouting() {
		super(74, "Scouting!");
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		if(thePlayer.getCars()>=1) {
			thePlayer.gainCash(10000, this);
		}
		if(thePlayer.getCars()>=1) {
			for(GreedPlayer otherPlayer : theGame.getPlayers()) {
				if(otherPlayer!=thePlayer) {
					otherPlayer.changeCash(-10000, this);
				}
			}
		}
		if(thePlayer.getKeys()>=1) {
			thePlayer.drawCard(theGame);
		}
	}
}
