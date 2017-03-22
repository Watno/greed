package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Thug;
import greed.cards.effects.LouieSavoirOFarrellEvent;

public class LouieSavoirOFarrell extends Thug {
	
	public LouieSavoirOFarrell() {
		super(67, "Louie \"Savoir\" O'Farrell", 1, 0, 1);
	}
	
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		theGame.addNextTurnEvent(new LouieSavoirOFarrellEvent(theGame, thePlayer, timingNumber, this));
	}
}