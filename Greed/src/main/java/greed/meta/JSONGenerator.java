package greed.meta;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import greed.game.*;

public class JSONGenerator {
	private static  JsonObject cardToJSON(GreedCard theCard) {
		JsonObject json = new JsonObject();
		json.addProperty("timingNumber", theCard.getTimingNumber());
		json.addProperty("name", theCard.getName());
		if (theCard instanceof Holding) {
			Holding cardAsHolding = (Holding) theCard;
			json.addProperty("bottles", cardAsHolding.getBottles());
			json.addProperty("hearts", cardAsHolding.getHearts());
			json.addProperty("wrenches", cardAsHolding.getWrenches());
		}
		if (theCard instanceof Thug) {
			Thug cardAsThug = (Thug) theCard;
			json.addProperty("cars", cardAsThug.getCars());
			json.addProperty("guns", cardAsThug.getGuns());
			json.addProperty("keys", cardAsThug.getKeys());
		}
		return json;
	}
	
	private static JsonObject holdingToJSON(Holding theHolding) {
		JsonObject json = cardToJSON(theHolding);
		json.addProperty("markers", theHolding.getMarkers());
		return json;
	}
	
	private static JsonObject thugToJSON(Thug theThug) {
		return cardToJSON(theThug);
	}
	
	private static JsonObject actionToJSON(Action theAction) {
		return cardToJSON(theAction);
	}
	
	private static JsonObject playerToJSONpublic(GreedPlayer thePlayer) {
		JsonObject json = new JsonObject();
		json.addProperty("name", thePlayer.getName());
		json.addProperty("cash", thePlayer.getCash());
		json.addProperty("bottles", thePlayer.getBottles());
		json.addProperty("hearts", thePlayer.getHearts());
		json.addProperty("wrenches", thePlayer.getWrenches());
		json.addProperty("cars", thePlayer.getCars());
		json.addProperty("guns", thePlayer.getGuns());
		json.addProperty("keys", thePlayer.getKeys());
		json.addProperty("handsize", thePlayer.getHand().size());
		JsonArray holdings = new JsonArray();
		for (Holding theHolding : thePlayer.getHoldings()) {
			holdings.add(holdingToJSON(theHolding));
		}
		json.add("holdings", holdings);
		JsonArray thugs = new JsonArray();
		for (Thug theThug : thePlayer.getThugs()) {
			thugs.add(thugToJSON(theThug));
		}
		json.add("thugs", thugs);
		JsonArray actions = new JsonArray();
		for (Action theAction : thePlayer.getActions()) {
			actions.add(actionToJSON(theAction));
		}
		json.add("actions", actions);
		return json;
	}
	
	private static JsonObject gameToJSONpublic(GreedGame theGame) {
		JsonObject json = new JsonObject();
		json.addProperty("turnCounter", theGame.getTurnCounter());
		JsonArray players = new JsonArray();
		for (GreedPlayer thePlayer : theGame.getPlayers()) {
			players.add(playerToJSONpublic(thePlayer));
		}
		json.add("players", players);
		json.addProperty("log", theGame.getLogger().getLog());
		return json;
	}
	
	private static JsonObject privateInformation(GreedPlayer thePlayer, int position) {
		JsonObject json = new JsonObject();
		JsonArray hand = new JsonArray();
		synchronized(thePlayer.getHand()){
			for (GreedCard theCard : thePlayer.getHand()) {
				hand.add(cardToJSON(theCard));
			}
		}
		json.add("hand", hand);
		JsonArray draftPile = new JsonArray();
		synchronized(thePlayer.getDraftPile()) {
				for (GreedCard theCard : thePlayer.getDraftPile()) {
					draftPile.add(cardToJSON(theCard));
			}
		}
		json.add("draftPile", draftPile);
		json.addProperty("position", position);
		return json;
	}
	
	public static void generateJSON(GreedGame theGame) {
		JsonObject json = gameToJSONpublic(theGame);
		int position=0;
		for (GreedPlayer thePlayer : theGame.getPlayers()) {
			json.add("privateInformation", privateInformation(thePlayer, position));
			thePlayer.send(json);
			position++;
		}
	}
	
	public static JsonObject createPrompt(String request, boolean optional, String reason) {
		JsonObject json = new JsonObject();
		json.addProperty("request", request);
		json.addProperty("optional", optional);
		json.addProperty("reason", reason);
		return json;
	}
}
