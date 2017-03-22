package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Thug;
import greed.cards.effects.TedNapoleonBonhamEvent;

public class TedNapoleonBonham extends Thug {
	
	public TedNapoleonBonham() {
		super(72, "Ted \"Napoleon\" Bonham", 1, 0, 0);
	}
	
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame)  {
		theGame.addNextTurnEvent(new TedNapoleonBonhamEvent(theGame, timingNumber, this));
	}
}