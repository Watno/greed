package greed.cards;

import java.util.ArrayList;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Thug;
import greed.cards.effects.HarveyBrainsRatcliffeEvent;

public class HarveyBrainsRatcliffe extends Thug {
	
	public HarveyBrainsRatcliffe() {
		super(1, "Harvey \"Brains\" Ratcliffe", 0, 0, 1);
	}
	
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		ArrayList<GreedPlayer> playerlist = theGame.getPlayers();
		for (int i=0; i<playerlist.size(); i++) {
			GreedPlayer leftPlayer = playerlist.get(i);
			if (leftPlayer!= thePlayer && (((playerlist.get(Math.floorMod((i+1), playerlist.size())) == thePlayer) ))) {
			//if the other player is indeed to the left 
			theGame.addThisTurnEvent(new HarveyBrainsRatcliffeEvent(theGame, leftPlayer, timingNumber, this, thePlayer));
			}
		}
	}
}