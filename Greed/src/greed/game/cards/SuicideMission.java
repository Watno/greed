package greed.game.cards;

import greed.game.Action;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

public class SuicideMission extends Action {

	public SuicideMission() {
		super(35, "Suicide mission!");
	}
	
	@Override
	protected boolean payCost(GreedPlayer thePlayer, GreedGame theGame) {
		return (thePlayer.payThug(this)!=null);
	}
	
	@Override
	public boolean isPlayable(GreedPlayer thePlayer, GreedGame theGame) {
		super.isPlayable(thePlayer, theGame);
		return thePlayer.getNumberOfThugs()>=1;
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		thePlayer.gainCash(25000, this);
	}
	
}