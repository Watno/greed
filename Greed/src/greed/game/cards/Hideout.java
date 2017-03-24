package greed.game.cards;

import java.util.ArrayList;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;
import greed.game.Thug;

public class Hideout extends Holding {
	
	public Hideout() {
		super(55, "Hideout", 0, 0, 0);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
		for(Thug theThug : new ArrayList<Thug>(thePlayer.getThugs())) {
			if (theThug.getTurnPlayed()<theGame.getTurnCounter()) {
				theGame.getLogger().executeRules(thePlayer, theThug, executingCard);
				theThug.doRules(thePlayer, theGame, theThug);
				theGame.getLogger().unindent();
			}
		}
	}
}
