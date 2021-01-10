package greed.game;

import com.google.gson.JsonObject;

public interface IDecisionMaker {
    int pickDraftIndex();

    int pickHandIndex();

    int pickThugIndex(Reason reason);

    int pickHoldingIndex(Reason reason);

    int pickHandIndexOptional(Reason reason);

    int pickThugIndexOptional(Reason reason);

    int pickHoldingIndexOptional(Reason reason);

    boolean makeYesNoChoice(Reason reason);

    void sendGameState(JsonObject gameState);
}
