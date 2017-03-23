package greed.game.cards.effects;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.PlayPlan;
import greed.game.eventtypes.AfterPlaysResolveEvent;

public class LouieSavoirOFarrellEvent extends AfterPlaysResolveEvent{
	public LouieSavoirOFarrellEvent(GreedGame theGame, GreedPlayer profiteer, int timingNumber, GreedCard source) {
		super(theGame, profiteer, timingNumber, source);
	}
	
	@Override
	public void execute(GreedGame theGame) {
		PlayPlan playPlan = profiteer.makePlayPlan();
		if (playPlan!=null) {
			playPlan.execute(theGame);
		}
	}
	
}