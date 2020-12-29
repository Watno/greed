package greed.game.cards;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;
import greed.game.cards.effects.PeteRepeatFellEvent;

public class PeteRepeatFell extends Thug {
	
	public PeteRepeatFell() {
		super(68, "Pete \"Repeat\" Fell", 0, 0, 1);
	}
	
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
		theGame.addNextTurnEvent(new PeteRepeatFellEvent(theGame, thePlayer, timingNumber, executingCard));
	}
}