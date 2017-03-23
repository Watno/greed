package greed.meta;

import java.text.NumberFormat;
import java.util.Locale;

import greed.game.GreedCard;
import greed.game.GreedPlayer;
import greed.game.Holding;
import greed.game.IconReason;
import greed.game.PaymentReason;
import greed.game.Reason;
import greed.game.eventtypes.TriggeredEvent;

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
	
	private String reasonToString(Reason reason) {
		if (reason instanceof TriggeredEvent) {
			TriggeredEvent reasonAsEvent = (TriggeredEvent) reason;
			return (" due to " + reasonAsEvent.getSource().getName() + "'s effect");
		}
		if (reason instanceof PaymentReason) {
			PaymentReason reasonAsPayment = (PaymentReason) reason;
			return (" as payment for " + reasonAsPayment.getPaidCard().getName());
		}
		if (reason instanceof IconReason) {
			return (" for icons");
		}
		return "";
	}
	
	public void turnStart(int turnCounter) {
		log=log.concat("\nTurn " + turnCounter + " starts\n");
		
	}
	
	public void playCard(GreedPlayer thePlayer, GreedCard theCard) {
		addTabs();
		log=log.concat(thePlayer.getName() + " plays " + theCard.getTimingNumber() + " - "   + theCard.getName() + ".\n");
		indent();
	}
	
	public void changeCash(GreedPlayer thePlayer, int amount, Reason reason) {
		String reasonString = reasonToString(reason);
		String keyword = " gains ";
		if (amount<0) {
			keyword = " loses ";
			amount *= -1;
		}
		if (amount!=0) {
			addTabs();
			log=log.concat(thePlayer.getName() + keyword + "$"+NumberFormat.getNumberInstance(Locale.US).format(amount) + reasonString+".\n");
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

	public void placeMarkers(GreedPlayer thePlayer, int amount, Holding holding, Reason reason) {
		String reasonString = reasonToString(reason);
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
			log=log.concat(thePlayer.getName() + keyword1 + amount + keyword3+ keyword2 + holding.getName() +reasonString+".\n");
		}
		
	}
	public void loseCard(GreedPlayer thePlayer, GreedCard theCard, Reason reason) {
		String reasonString = reasonToString(reason);
		addTabs();
		log=log.concat(thePlayer.getName() + " loses " + theCard.getName() + reasonString + ".\n");
		indent();
	}
	
	public void activateEffect(TriggeredEvent effect) {
		addTabs();
		log=log.concat("The effect of " +effect.getCardOwner().getName()+"'s " + effect.getSource().getName() +" is active this turn.\n");
	}
		
	public void discardCard(GreedPlayer thePlayer, GreedCard theCard, Reason reason) {
		String reasonString = reasonToString(reason);
		addTabs();
		log=log.concat(thePlayer.getName() + " discards " + theCard.getName() + reasonString + ".\n");
	}
	
	public void putBackInHand(GreedPlayer thePlayer, GreedCard theCard, Reason reason) {
		String reasonString = reasonToString(reason);
		addTabs();
		log=log.concat(thePlayer.getName() + " puts " + theCard.getName() + " back in their hand "+ reasonString + ".\n");
	}

	public void doubleAmount(int newamount, Reason reason) {
		String reasonString = reasonToString(reason);
		if (newamount!=0) {
			addTabs();
			log=log.concat("Amount is doubled to $"+NumberFormat.getNumberInstance(Locale.US).format(newamount)  + reasonString + ".\n");
		}
	}
	
	public void executeRules(GreedPlayer thePlayer, GreedCard theCard, Reason reason) {
		String reasonString = reasonToString(reason);
		addTabs();
		log=log.concat(thePlayer.getName() + " executes the rules of " + theCard.getName() + reasonString +".\n");
		indent();
	}
	
	public void copyCard(GreedCard copyingCard, GreedCard copiedCard, Reason reason) {
		String reasonString = reasonToString(reason);
		addTabs();
		log=log.concat(copyingCard.getName() + " becomes a copy of " + copiedCard.getName() + reasonString +".\n");
	}
	
	public String getLog() {
		return log;
	}
}

