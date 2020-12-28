package greed.game;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import greed.game.cards.Seance;
import greed.game.eventtypes.TriggeredEvent;
import greed.meta.JSONGenerator;
import server.games.IUserFromGamePerspective;

import java.text.NumberFormat;
import java.util.Locale;

public class RealDecisionMaker implements IDecisionMaker {
	IUserFromGamePerspective connection;
	GreedGame theGame;
	GreedPlayer thePlayer;
	
	public RealDecisionMaker(IUserFromGamePerspective connection, GreedGame theGame, GreedPlayer thePlayer) {
		this.connection = connection;
		this.theGame = theGame;
		this.thePlayer = thePlayer;
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
		return requestIntInput(JSONGenerator.createPrompt("draftPile", false, "Pick a card to draft!"));
	}

	@Override
	public int pickHandIndex() {
		theGame.sendGameState();
		return requestIntInput(JSONGenerator.createPrompt("hand", false, "Pick a card to play!"));
	}

	@Override
	public int pickThugIndex(Reason reason) {
		theGame.sendGameState();
		String reasonString = reasonToString(reason);
		return requestIntInput(JSONGenerator.createPrompt("thug", false, "Pick a Thug"+reasonString+"!"));
	}

	@Override
	public int pickHoldingIndex(Reason reason) {
		theGame.sendGameState();
		String reasonString = reasonToString(reason);
		return requestIntInput(JSONGenerator.createPrompt("holding", false, "Pick a Holding"+reasonString+"!"));
	}

	@Override
	public int pickHandIndexOptional(Reason reason) {
		theGame.sendGameState();
		String reasonString = reasonToString(reason);
		return requestIntInput(JSONGenerator.createPrompt("hand", true, "You may pick a card from your Hand"+reasonString+"."));
	}

	@Override
	public int pickThugIndexOptional(Reason reason) {
		theGame.sendGameState();
		String reasonString = reasonToString(reason);
		return requestIntInput(JSONGenerator.createPrompt("thug", true, "You may pick a Thug"+reasonString+"."));
	}

	@Override
	public int pickHoldingIndexOptional(Reason reason) {
		theGame.sendGameState();
		String reasonString = reasonToString(reason);
		return requestIntInput(JSONGenerator.createPrompt("holding", true, "You may pick a Holding"+reasonString+"."));
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
		return requestBoolInput(JSONGenerator.createPrompt("YesNo", true, "Do you want to" +choice+ "?")); //yes is 0, no is expected to be -1
	}
	
	@Override
	public void sendGameState(JsonObject gameState) {
		//TODO Cleanup gamestate sending
		connection.send(gameState);
	}

	private boolean requestBoolInput(JsonObject prompt) {
		return requestInput(prompt).getAsInt()==0; //yes is 0, no is expected to be -1
	}
	
	
	private int requestIntInput(JsonObject prompt) {
		return requestInput(prompt).getAsInt();
	}
	
	private JsonElement requestInput(JsonObject prompt) {
		JsonElement input = connection.requestInput(prompt);
		if (connection.hasResigned()) {
			thePlayer.replaceByBot();
			return new JsonPrimitive(0);
		}
		return input;
	}


}
