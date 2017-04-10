package greed.game.cards;

import java.util.ArrayList;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;
import greed.game.cards.effects.HarveyBrainsRatcliffeEvent;

public class HarveyBrainsRatcliffe extends Thug {
	
	public HarveyBrainsRatcliffe() {
		super(1, "Harvey \"Brains\" Ratcliffe", 0, 0, 1);
	}
	
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
		ArrayList<GreedPlayer> playerlist = theGame.getPlayers();
		for (int i=0; i<playerlist.size(); i++) {
			GreedPlayer leftPlayer = playerlist.get(i);
			if (leftPlayer!= thePlayer && (((playerlist.get(Math.floorMod((i-1), playerlist.size())) == thePlayer) ))) {
			//if the other player is indeed to the left 
			theGame.addThisTurnEvent(new HarveyBrainsRatcliffeEvent(theGame, leftPlayer, timingNumber, executingCard, thePlayer));
			}
		}
	}
}