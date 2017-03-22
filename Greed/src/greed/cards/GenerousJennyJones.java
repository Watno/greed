package greed.cards;

import greed.GreedPlayer;
import greed.GreedGame;
import greed.Thug;
import greed.cards.effects.GenerousJennyJonesEvent;

public class GenerousJennyJones extends Thug {
	
	public GenerousJennyJones() {
		super(24, "\"Generous\" Jenny Jones", 0, 1, 0);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		thePlayer.gainCash(20000, "");
	}
	
	@Override
	protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
		super.putInPlay(thePlayer, theGame);
		addPermanentEffect(new GenerousJennyJonesEvent(theGame, thePlayer, timingNumber, this));
	}
}