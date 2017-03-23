package greed.game.cards;

import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;
import greed.game.cards.effects.SandysSnookerNSchnapsEvent;

public class SandysSnookerNSchnaps extends Holding {
	
	public SandysSnookerNSchnaps() {
		super(17, "Sandy's Snooker 'n' Schnaps", 1, 0, 0);
	}
	
	@Override
	public boolean payCost(GreedPlayer thePlayer, GreedGame theGame) {
		return thePlayer.payCashCost(20000, this);
	}
	
	@Override
	public boolean isPlayable(GreedPlayer thePlayer, GreedGame theGame) {
		super.isPlayable(thePlayer, theGame);
		return thePlayer.getCash()>=20000;
	}
	
	
	@Override
	protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
		super.putInPlay(thePlayer, theGame);
		addPermanentEffect(new SandysSnookerNSchnapsEvent(theGame, thePlayer, timingNumber, this));
	}
}
