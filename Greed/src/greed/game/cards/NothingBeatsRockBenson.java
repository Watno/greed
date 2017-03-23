package greed.game.cards;

import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;
import greed.game.cards.effects.NothingBeatsRockBensonEvent;

public class NothingBeatsRockBenson extends Thug {
	
	public NothingBeatsRockBenson() {
		super(70, "\"Nothing beats\" Rock Benson", 0, 1, 0);
	}
	
	@Override
	protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
		super.putInPlay(thePlayer, theGame);
		addPermanentEffect(new NothingBeatsRockBensonEvent(theGame, thePlayer, timingNumber, this));
	}
}