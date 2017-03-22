package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;
import greed.Thug;

public class PeepingTomThumb extends Thug {
	
	public PeepingTomThumb() {
		super(57, "\"Peeping\" Tom \"Thumb\"", 0, 2, 0);
	}
	
	
	@Override
	public boolean payCost(GreedPlayer thePlayer, GreedGame theGame) {
		return thePlayer.payHolding(theGame, this)!=null;
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		int mostMarkers = 0;
		for (GreedPlayer anyPlayer : theGame.getPlayers()) {
			for (Holding theHolding : anyPlayer.getHoldings()) {
				mostMarkers = Math.max(mostMarkers, theHolding.getMarkers());
			}
		}
		thePlayer.gainCash(mostMarkers*5000, "");
	}
}