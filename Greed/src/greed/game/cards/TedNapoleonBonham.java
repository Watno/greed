package greed.game.cards;

import greed.cards.effects.TedNapoleonBonhamEvent;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;

public class TedNapoleonBonham extends Thug {
	
	public TedNapoleonBonham() {
		super(72, "Ted \"Napoleon\" Bonham", 1, 0, 0);
	}
	
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame)  {
		theGame.addNextTurnEvent(new TedNapoleonBonhamEvent(theGame, timingNumber, this));
	}
}