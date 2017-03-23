package greed.meta;

import java.text.NumberFormat;
import java.util.Locale;

import greed.game.GreedCard;
import greed.game.GreedPlayer;
import greed.game.Holding;

public class Logger {
	private int indentation = 0;
	String log = "";
	
	public void indent() {
		indentation++;
	}
	public void unindent() {
		indentation--;
	}
	
	private void addTabs() {
		for (int i=0; i<indentation; i++) {
			log=log.concat("&emsp;");
		}
	}
	
	public void turnStart(int turnCounter) {
		log=log.concat("\nTurn " + turnCounter + " starts\n");
		
	}
	
	public void playCard(GreedPlayer thePlayer, GreedCard theCard) {
		addTabs();
		log=log.concat(thePlayer.getName() + " plays "  + theCard.getName() + " (" + theCard.getTimingNumber() +  ").\n");
		indent();
	}
	
	public void changeCash(GreedPlayer thePlayer, int amount, String reason) {
		String keyword = " gains ";
		if (amount<0) {
			keyword = " loses ";
			amount *= -1;
		}
		if (amount!=0) {
			addTabs();
			log=log.concat(thePlayer.getName() + keyword + "$"+NumberFormat.getNumberInstance(Locale.US).format(amount) + reason+".\n");
		}
	}
	
	public void cantAffordCard(GreedPlayer thePlayer, GreedCard theCard) {
		addTabs();
		log=log.concat(thePlayer.getName() + " doesn't fulfill the requirements of "  + theCard.getName() + " (" + theCard.getTimingNumber()  + ").\n");
	}
	
	public void reportScore(GreedPlayer thePlayer, int amount) {
		addTabs();
		log=log.concat(thePlayer.getName() + " made $" + NumberFormat.getNumberInstance(Locale.US).format(amount) + ".\n");
	}

	public void announceGameEnd() {
		log=log.concat("\nEnd of Game\n");
	}

	public void placeMarkers(GreedPlayer thePlayer, int amount, Holding holding, String reason) {
		String keyword1 = " places ";
		String keyword2 = " on ";
		String keyword3 = " markers";
		if (amount<0) {
			keyword1 = " removes ";
			keyword2 = " from ";
			amount *= -1;
		}
		if (amount==1) {
			keyword3= " marker";
		}
		if (amount!=0) {
			addTabs();
			log=log.concat(thePlayer.getName() + keyword1 + amount + keyword3+ keyword2 + holding.getName() +reason+".\n");
		}
		
	}
	public void loseCard(GreedPlayer thePlayer, GreedCard theCard, String reason) {
		addTabs();
		log=log.concat(thePlayer.getName() + " loses " + theCard.getName() + reason + ".\n");
		indent();
	}
		

	public String getLog() {
		return log;
	}
}

