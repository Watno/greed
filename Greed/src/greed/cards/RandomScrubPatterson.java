package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Thug;

public class RandomScrubPatterson extends Thug {
	
	public RandomScrubPatterson	() {
		super(65, "\"Random\" Scrub Patterson", 0, 0, 1);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		int amount = thePlayer.getKeys();
		for (int i=0; i<amount; i++) {
			thePlayer.drawCard(theGame);
		}
	}
}
