package greed.game.cards;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;
import greed.game.cards.effects.TedNapoleonBonhamEvent;

public class TedNapoleonBonham extends Thug {
	
	public TedNapoleonBonham() {
		super(72, "Ted \"Napoleon\" Bonham", 1, 0, 0);
	}
	
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard)  {
		theGame.addNextTurnEvent(new TedNapoleonBonhamEvent(theGame, timingNumber, executingCard));
	}
}