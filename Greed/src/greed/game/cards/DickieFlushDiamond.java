package greed.game.cards;

import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;

public class DickieFlushDiamond extends Thug {
	
	public DickieFlushDiamond() {
		super(22, "Dickie \"Flush\" Diamond", 0, 1, 0);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		thePlayer.gainCash(10000, this);
	}
}