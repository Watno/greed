package greed.game.cards;

import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;

public class TommysGunsNAmmo extends Holding {
	
	public TommysGunsNAmmo() {
		super(58, "Tommy's Guns 'n' Ammo", 0, 0, 1);
	}
	
	@Override
	public boolean payCost(GreedPlayer thePlayer, GreedGame theGame) {
		return (thePlayer.payHolding(theGame, this)!=null);
	}
	
	@Override
	public boolean isPlayable(GreedPlayer thePlayer, GreedGame theGame) {
		super.isPlayable(thePlayer, theGame);
		return thePlayer.getNumberOfHoldings()>=1;
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		for (GreedPlayer otherPlayer: theGame.getPlayers()) {
			if (otherPlayer!=thePlayer) {
				for (Holding theHolding: otherPlayer.getHoldings()) {
					theHolding.changeMarkers(-1, this);
				}
			}
		}
	}
}