package greed.cards;

import java.util.ArrayList;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Holding;
import greed.Thug;

public class Hideout extends Holding {
	
	public Hideout() {
		super(55, "Hideout", 0, 0, 0);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		for(Thug theThug : new ArrayList<Thug>(thePlayer.getThugs())) {
			if (theThug.getTurnPlayed()<theGame.getTurnCounter()) {
				theThug.doRules(thePlayer, theGame);
			}
		}
	}
}
