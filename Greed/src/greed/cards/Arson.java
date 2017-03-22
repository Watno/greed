package greed.cards;

import greed.Action;
import greed.GreedGame;
import greed.GreedPlayer;

public class Arson extends Action {

	public Arson() {
		super(75, "Arson!");
	}
	
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		int amount = -thePlayer.getNumberOfThugs()*10000;
		for (GreedPlayer otherPlayer: theGame.getPlayers()) {
			if (!otherPlayer.equals(thePlayer)) {
				otherPlayer.changeCash(amount, "");
			}
		}
	}
	
}