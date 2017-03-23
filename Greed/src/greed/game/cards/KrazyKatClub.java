package greed.game.cards;

import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;

public class KrazyKatClub extends Holding {
	
	public KrazyKatClub() {
		super(47, "Krazy Kat Club", 1, 1, 0);
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return (thePlayer.getGuns()>=2);
	}
}