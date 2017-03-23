package greed.game.cards;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;

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
