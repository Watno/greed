package greed.game.cards;

import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;
import greed.game.Thug;

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
		thePlayer.gainCash(mostMarkers*5000, this);
	}
}