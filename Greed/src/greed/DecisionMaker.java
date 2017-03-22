package greed;

public interface DecisionMaker {
	public int pickDraftIndex();
	public int pickHandIndex();
	public int pickThugIndex(String reason);
	public int pickHoldingIndex(String reason);
	public int pickHandIndexOptional(String reason);
	public int pickThugIndexOptional(String reason);
	public int pickHoldingIndexOptional(String reason);
	public boolean makeYesNoChoice();
	public void sendGameState(String gameState);
}
