package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Thug;
import greed.eventypes.IgnoreCostEvent;

public class ViciousSydVarney extends Thug {
	
	public ViciousSydVarney() {
		super(63, "\"Vicious\" Syd Varney", 0, 1, 0);
	}
	
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame)  {
		theGame.addNextTurnEvent(new IgnoreCostEvent(theGame, thePlayer, timingNumber, this));
	}
}