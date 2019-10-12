package greed.game;

import com.google.gson.JsonObject;

public interface IDecisionMaker {
	public int pickDraftIndex();
	public int pickHandIndex();
	public int pickThugIndex(Reason reason);
	public int pickHoldingIndex(Reason reason);
	public int pickHandIndexOptional(Reason reason);
	public int pickThugIndexOptional(Reason reason);
	public int pickHoldingIndexOptional(Reason reason);
	public boolean makeYesNoChoice(Reason reason);
	public void sendGameState(JsonObject gameState);
}
