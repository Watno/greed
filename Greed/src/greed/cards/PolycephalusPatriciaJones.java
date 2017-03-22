package greed.cards;

import greed.GreedGame;
import greed.GreedPlayer;
import greed.Thug;
import greed.GreedCard;

public class PolycephalusPatriciaJones extends Thug {

	public PolycephalusPatriciaJones() {
		super(53, "\"Polycephalus\" Patricia Jones", 0, 0, 0);
	}
	
	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		GreedCard theCard;
		do {
			theCard=theGame.draw();
		} while (!(theCard instanceof Thug)&&theCard!=null);
		if(theCard!=null){
			theCard.play(thePlayer, theGame);			
		}
	}
	
}
