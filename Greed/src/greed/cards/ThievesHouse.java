package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;

public class ThievesHouse extends Holding {
	
	public ThievesHouse() {
		super(51, "Thieves' House", 1, 0, 1);
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return (thePlayer.getKeys()>=2);
	}
}