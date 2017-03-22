package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;
import greed.cards.effects.JennysWaterfrontDiveEvent;

public class JennysWaterfrontDive extends Holding {
	
	public JennysWaterfrontDive() {
		super(46, "Jenny's Waterfront Dive", 0, 0, 0);
	}
	
	
	@Override
	protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
		super.putInPlay(thePlayer, theGame);
		addPermanentEffect(new JennysWaterfrontDiveEvent(theGame, thePlayer, timingNumber, this));
	}
}