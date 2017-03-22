package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Thug;

public class EdCheeseClothMcGinty extends Thug {
	
	public EdCheeseClothMcGinty() {
		super(23, "Ed \"Cheesecloth\" McGinty", 0, 1, 1);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		thePlayer.gainCash(thePlayer.getGuns()*5000, "");
	}
}
