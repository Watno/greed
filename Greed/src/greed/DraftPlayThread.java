package greed;

import java.util.List;

public class DraftPlayThread implements Runnable {
	int turnCounter;
	GreedPlayer thePlayer;
	List<PlayPlan> playPlans;
	
	public DraftPlayThread(int turnCounter, GreedPlayer thePlayer, List<PlayPlan> playPlans) {
		this.turnCounter=turnCounter;
		this.thePlayer=thePlayer;
		this.playPlans=playPlans;
	}
	@Override
	public void run() {
		thePlayer.draft();
		if(turnCounter>=3){
			playPlans.add(thePlayer.makePlayPlan());
		}
	}

}
