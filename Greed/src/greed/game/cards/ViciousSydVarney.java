package greed.game.cards;

import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;
import greed.game.eventtypes.IgnoreCostEvent;

public class ViciousSydVarney extends Thug {
	
	public ViciousSydVarney() {
		super(63, "\"Vicious\" Syd Varney", 0, 1, 0);
	}
	
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame)  {
		theGame.addNextTurnEvent(new IgnoreCostEvent(theGame, thePlayer, timingNumber, this));
	}
}