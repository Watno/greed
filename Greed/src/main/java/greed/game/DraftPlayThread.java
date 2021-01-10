package greed.game;

import java.util.List;

public class DraftPlayThread implements Runnable {
	final int turnCounter;
	final GreedPlayer thePlayer;
	final GreedGame theGame;
	final List<PlayPlan> playPlans;
	
	public DraftPlayThread(int turnCounter, GreedPlayer thePlayer, List<PlayPlan> playPlans, GreedGame theGame) {
		this.turnCounter=turnCounter;
		this.thePlayer=thePlayer;
		this.playPlans=playPlans;
		this.theGame=theGame;
	}
	@Override
	public void run() {
		thePlayer.draft();
		if(turnCounter>=3){
			playPlans.add(thePlayer.makePlayPlan());
		}
		theGame.sendGameState();
	}

}
