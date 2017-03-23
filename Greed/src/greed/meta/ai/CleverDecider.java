package greed.meta.ai;

import java.util.List;
import java.util.Random;

import greed.game.DecisionMaker;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Reason;

public class CleverDecider implements DecisionMaker {
	
	GreedPlayer player;
	GreedGame game;
	
	public CleverDecider(GreedPlayer player, GreedGame game) {
		super();
		this.player = player;
		this.game = game;
	}

	private int pickBest(List<GreedCard> choiceList) {
		int currentIndex = 0;
		int bestIndex = 0;
		int numberChanges = 0;
		Random randomizer=new Random();
		for (GreedCard theCard: choiceList) {
			if (theCard.isPlayable(player, game)) {
				if (randomizer.nextInt(numberChanges+1)==numberChanges) {
					bestIndex=currentIndex;
					numberChanges++;
				}
			}
			currentIndex++;
		}
		return bestIndex;
	}

	@Override
	public int pickDraftIndex() {
		return pickBest(player.getDraftPile());
	}

	@Override
	public int pickHandIndex() {
		return pickBest(player.getHand());
	}

	@Override
	public int pickThugIndex(Reason reason) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int pickHoldingIndex(Reason reason) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int pickHandIndexOptional(Reason reason) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int pickThugIndexOptional(Reason reason) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int pickHoldingIndexOptional(Reason reason) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean makeYesNoChoice(Reason reason) {
		return true;
	}

	@Override
	public void sendGameState(String gameState) {
		// TODO Auto-generated method stub

	}

}
