package greed.game.cards;

import greed.cards.effects.LouieSavoirOFarrellEvent;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;

public class LouieSavoirOFarrell extends Thug {
	
	public LouieSavoirOFarrell() {
		super(67, "Louie \"Savoir\" O'Farrell", 1, 0, 1);
	}
	
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		theGame.addNextTurnEvent(new LouieSavoirOFarrellEvent(theGame, thePlayer, timingNumber, this));
	}
}