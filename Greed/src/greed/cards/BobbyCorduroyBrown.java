package greed.cards;

import greed.GreedPlayer;
import greed.GreedGame;
import greed.Thug;

public class BobbyCorduroyBrown extends Thug {
	
	public BobbyCorduroyBrown() {
		super(76, "Bobby \"Corduroy\" Brown", 0, 1, 0);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		for (GreedPlayer otherPlayer: theGame.getPlayers()) {
			if(!thePlayer.equals(otherPlayer)) {
				otherPlayer.changeCash(-10000, "");
			}
		}
	}
}
