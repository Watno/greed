package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Thug;

public class JackCrackerThompson extends Thug {

	public JackCrackerThompson () {
		super(77, "Jack \"Cracker\" Thompson ", 0, 0, 1);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		for (GreedPlayer otherPlayer : theGame.getPlayers()) {
			if (!otherPlayer.equals(thePlayer)) {
				otherPlayer.changeCash(-otherPlayer.getNumberOfHoldings()*5000, "");
			}
		}
	}
}
