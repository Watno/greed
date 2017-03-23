package greed.game.cards;

import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;
import greed.game.cards.effects.EugeneTheButcherMidgeEvent;

public class EugeneTheButcherMidge extends Thug {
	
	public EugeneTheButcherMidge() {
		super(71, "Eugene \"The Butcher\" Midge", 1, 1, 0);
	}
	
	@Override
	public boolean payCost(GreedPlayer thePlayer, GreedGame theGame) {
		return (thePlayer.payThug(this)!=null);
	}
	
	@Override
	public boolean isPlayable(GreedPlayer thePlayer, GreedGame theGame) {
		super.isPlayable(thePlayer, theGame);
		return thePlayer.getNumberOfThugs()>=1;
	}
	
	
	@Override
	protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
		super.putInPlay(thePlayer, theGame);
		addPermanentEffect(new EugeneTheButcherMidgeEvent(theGame, thePlayer, timingNumber, this));
	}
}