package greed.game.cards;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;
import greed.game.cards.effects.LouieSavoirOFarrellEvent;

public class LouieSavoirOFarrell extends Thug {
	
	public LouieSavoirOFarrell() {
		super(67, "Louie \"Savoir\" O'Farrell", 1, 0, 1);
	}
	
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
		theGame.addNextTurnEvent(new LouieSavoirOFarrellEvent(theGame, thePlayer, timingNumber, executingCard));
	}
}