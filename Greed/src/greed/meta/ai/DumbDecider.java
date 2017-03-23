package greed.meta.ai;

import greed.game.DecisionMaker;
import greed.game.Reason;

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
	public int pickThugIndex(Reason reason) {
		return 0;
	}

	@Override
	public int pickHoldingIndex(Reason reason) {
		return 0;
	}

	@Override
	public int pickHandIndexOptional(Reason reason) {
		return 0;
	}

	@Override
	public int pickThugIndexOptional(Reason reason) {
		return 0;
	}

	@Override
	public int pickHoldingIndexOptional(Reason reason) {
		return 0;
	}

	@Override
	public boolean makeYesNoChoice(Reason reason) {
		return true;
	}
	
	@Override
	public void sendGameState(String gameState) {
	}

}
