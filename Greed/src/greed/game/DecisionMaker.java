package greed.game;

public interface DecisionMaker {
	public int pickDraftIndex();
	public int pickHandIndex();
	public int pickThugIndex(Reason reason);
	public int pickHoldingIndex(Reason reason);
	public int pickHandIndexOptional(Reason reason);
	public int pickThugIndexOptional(Reason reason);
	public int pickHoldingIndexOptional(Reason reason);
	public boolean makeYesNoChoice(Reason reason);
	public void sendGameState(String gameState);
}
