package greed.game.cards;

import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;

public class HalloweenJackParis extends Thug {
	public HalloweenJackParis() {
		super(62, "\"Halloween\" Jack Paris", 0, 0, 1);
	}
	
	@Override
	public void removeFromPlay(GreedPlayer thePlayer, GreedGame theGame, String reason) {
		super.removeFromPlay(thePlayer, theGame, reason);
		thePlayer.gainCash(20000, "");
	}
}