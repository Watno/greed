package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;

public class KrazyKatClub extends Holding {
	
	public KrazyKatClub() {
		super(47, "Krazy Kat Club", 1, 1, 0);
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return (thePlayer.getGuns()>=2);
	}
}