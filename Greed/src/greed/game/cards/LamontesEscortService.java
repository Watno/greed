package greed.game.cards;

import greed.cards.effects.LamontesEscortServiceEvent;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;

public class LamontesEscortService extends Holding {
	
	public LamontesEscortService() {
		super(13, "Lamonte's Escort Service", 0, 1, 0);
	}
	
	@Override
	public boolean payCost(GreedPlayer thePlayer, GreedGame theGame) {
		return thePlayer.payCashCost(5000, this);
	}
	
	@Override
	public boolean isPlayable(GreedPlayer thePlayer, GreedGame theGame) {
		super.isPlayable(thePlayer, theGame);
		return thePlayer.getCash()>=5000;
	}
	
	@Override
	protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
		super.putInPlay(thePlayer, theGame);
		addPermanentEffect(new LamontesEscortServiceEvent(theGame, thePlayer, timingNumber, this));
	}
}
