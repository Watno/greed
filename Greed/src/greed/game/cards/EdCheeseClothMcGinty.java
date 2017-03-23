package greed.game.cards;

import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;

public class EdCheeseClothMcGinty extends Thug {
	
	public EdCheeseClothMcGinty() {
		super(23, "Ed \"Cheesecloth\" McGinty", 0, 1, 1);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		thePlayer.gainCash(thePlayer.getGuns()*5000, this);
	}
}
