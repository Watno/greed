package greed.meta;

import java.text.NumberFormat;
import java.util.Locale;

import greed.game.DecisionMaker;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.MoneyPaymentReason;
import greed.game.PaymentReason;
import greed.game.Reason;
import greed.game.cards.Seance;
import greed.game.eventtypes.TriggeredEvent;

public class RealDecisionMaker implements DecisionMaker {
	GreedConnection connection;
	GreedGame theGame;
	GreedPlayer thePlayer;
	
	public RealDecisionMaker(GreedConnection connection, GreedGame theGame, GreedPlayer thePlayer) {
		this.connection = connection;
		this.theGame = theGame;
		this.thePlayer = thePlayer;
		connection.setDecider(this);
	}
	
	private String reasonToString(Reason reason) {
		if (reason instanceof TriggeredEvent) {
			TriggeredEvent reasonAsEvent = (TriggeredEvent) reason;
			return (" for " + reasonAsEvent.getSource().getName() + "'s effect");
		}
		if (reason instanceof PaymentReason) {
			PaymentReason reasonAsPayment = (PaymentReason) reason;
			return (" as payment for " + reasonAsPayment.getPaidCard().getName());
		}
		if (reason instanceof GreedCard) {
			GreedCard cardReason = (GreedCard) reason;
			return (" for " + cardReason.getName());
		}
		return "";
	}

	@Override
	public int pickDraftIndex() {
		theGame.sendGameState();
		return connection.requestInput(JSONGenerator.createPrompt("draftPile", false, "Pick a card to draft!"));
	}

	@Override
	public int pickHandIndex() {
		theGame.sendGameState();
		return connection.requestInput(JSONGenerator.createPrompt("hand", false, "Pick a card to play!"));
	}

	@Override
	public int pickThugIndex(Reason reason) {
		theGame.sendGameState();
		String reasonString = reasonToString(reason);
		return connection.requestInput(JSONGenerator.createPrompt("thug", false, "Pick a Thug"+reasonString+"!"));
	}

	@Override
	public int pickHoldingIndex(Reason reason) {
		theGame.sendGameState();
		String reasonString = reasonToString(reason);
		return connection.requestInput(JSONGenerator.createPrompt("holding", false, "Pick a Holding"+reasonString+"!"));
	}

	@Override
	public int pickHandIndexOptional(Reason reason) {
		theGame.sendGameState();
		String reasonString = reasonToString(reason);
		return connection.requestInput(JSONGenerator.createPrompt("hand", true, "You may pick a card from your Hand"+reasonString+"."));
	}

	@Override
	public int pickThugIndexOptional(Reason reason) {
		theGame.sendGameState();
		String reasonString = reasonToString(reason);
		return connection.requestInput(JSONGenerator.createPrompt("thug", true, "You may pick a Thug"+reasonString+"!"));
	}

	@Override
	public int pickHoldingIndexOptional(Reason reason) {
		theGame.sendGameState();
		String reasonString = reasonToString(reason);
		return connection.requestInput(JSONGenerator.createPrompt("holding", true, "You may pick a Holding"+reasonString+"!"));
	}

	@Override
	public boolean makeYesNoChoice(Reason reason) {
		theGame.sendGameState();
		String choice ="";
		if (reason instanceof Seance) {
			choice = " play another card";
		}
		if (reason instanceof MoneyPaymentReason) {
			MoneyPaymentReason moneyReason = (MoneyPaymentReason) reason;
			choice = " pay $" + NumberFormat.getNumberInstance(Locale.US).format(moneyReason.getAmount());
			choice += " for " + moneyReason.getPaidCard().getName();
		}
		return connection.requestInput(JSONGenerator.createPrompt("YesNo", true, "Do you want to" +choice+ "?"))==0; //yes is 0, no is expected to be -1
	}
	
	@Override
	public void sendGameState(String gameState) {
		connection.sendState(gameState);
	}
	
	public void replaceByBot() {
		thePlayer.replaceByBot();//still need to handle currently awaited decision by old decider
	}


}
