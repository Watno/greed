package greed.game.cards;

import greed.game.Action;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.cards.effects.ShakedownEvent;

import java.util.ArrayList;

public class Shakedown extends Action {

	public Shakedown() {
		super(3, "Shakedown!");
	}
	
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
		thePlayer.gainCash(10000, executingCard);
		ArrayList<GreedPlayer> playerlist = theGame.getPlayers();
		for (int i=0; i<playerlist.size(); i++) {
			GreedPlayer adjacentPlayer = playerlist.get(i);
			if (adjacentPlayer!= thePlayer && (((playerlist.get(Math.floorMod((i+1), playerlist.size())) == thePlayer) || (playerlist.get(Math.floorMod((i-1), playerlist.size())) == thePlayer)))) {
			//if the adjacent player is indeed adjacent
			theGame.addThisTurnEvent(new ShakedownEvent(theGame, adjacentPlayer, timingNumber, executingCard, thePlayer));
			}
		}
	}
}