package greed.game.cards;

import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;
import greed.game.cards.effects.GenerousJennyJonesEvent;

public class GenerousJennyJones extends Thug {
	
	public GenerousJennyJones() {
		super(24, "\"Generous\" Jenny Jones", 0, 1, 0);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		thePlayer.gainCash(20000, this);
	}
	
	@Override
	protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
		super.putInPlay(thePlayer, theGame);
		addPermanentEffect(new GenerousJennyJonesEvent(theGame, thePlayer, timingNumber, this));
	}
}