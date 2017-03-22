package greed;

public class DumbDecider implements DecisionMaker{

	@Override
	public int pickDraftIndex() {
		return 0;
	}

	@Override
	public int pickHandIndex() {
		return 0;
	}

	@Override
	public int pickThugIndex(String reason) {
		return 0;
	}

	@Override
	public int pickHoldingIndex(String reason) {
		return 0;
	}

	@Override
	public int pickHandIndexOptional(String reason) {
		return 0;
	}

	@Override
	public int pickThugIndexOptional(String reason) {
		return 0;
	}

	@Override
	public int pickHoldingIndexOptional(String reason) {
		return 0;
	}

	@Override
	public boolean makeYesNoChoice() {
		return true;
	}
	
	@Override
	public void sendGameState(String gameState) {
	}

}
