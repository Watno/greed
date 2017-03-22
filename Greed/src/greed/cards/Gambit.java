package greed.cards;

import greed.Action;
import greed.GreedGame;
import greed.GreedPlayer;


public class Gambit extends Action {

	public Gambit() {
		super(27, "Gambit!");
	}
	
	@Override
	public boolean payCost(GreedPlayer thePlayer, GreedGame theGame) {
		return (thePlayer.payHandCard(theGame, this)!=null);
	}
	
	@Override
	public boolean isPlayable(GreedPlayer thePlayer, GreedGame theGame) {
		super.isPlayable(thePlayer, theGame);
		return thePlayer.getHand().size()>=1;
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		thePlayer.gainCash(30000, "");
	}
	
	
}