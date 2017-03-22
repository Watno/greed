package greed.cards;

import greed.Action;
import greed.GreedGame;
import greed.GreedPlayer;

public class SuicideMission extends Action {

	public SuicideMission() {
		super(35, "Suicide mission!");
	}
	
	@Override
	protected boolean payCost(GreedPlayer thePlayer, GreedGame theGame) {
		return (thePlayer.payThug(theGame, this)!=null);
	}
	
	@Override
	public boolean isPlayable(GreedPlayer thePlayer, GreedGame theGame) {
		super.isPlayable(thePlayer, theGame);
		return thePlayer.getNumberOfThugs()>=1;
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		thePlayer.gainCash(25000, "");
	}
	
}