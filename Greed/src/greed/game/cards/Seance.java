package greed.game.cards;

import greed.game.Action;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;
import greed.game.PlayPlan;


public class Seance extends Action {

	public Seance() {
		super(80, "Seance!");
	}
	
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
		thePlayer.gainCash(10000, executingCard);
		Holding theHolding = thePlayer.chooseHolding(this);
		if (theHolding!= null) {
			theHolding.changeMarkers(1, executingCard);
		}
		if (thePlayer.makeYesNoChoice(this)) {
			PlayPlan playPlan = thePlayer.makePlayPlan();
			if (playPlan!=null) {
				playPlan.execute(theGame);
			}
		}
		
	}
	
	
}