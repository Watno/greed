package greed.cards;

import greed.Action;
import greed.GreedGame;
import greed.GreedPlayer;

public class InsiderTrading extends Action {

	public InsiderTrading() {
		super(29, "Insider trading!");
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return (thePlayer.getCash()>=90000);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		thePlayer.gainCash(45000, "");
	}
}