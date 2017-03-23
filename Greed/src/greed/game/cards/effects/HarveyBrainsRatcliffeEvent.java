package greed.game.cards.effects;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.eventtypes.CashGainEvent;

public class HarveyBrainsRatcliffeEvent extends CashGainEvent{
	private GreedPlayer profiteer;
	
	public HarveyBrainsRatcliffeEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source, GreedPlayer profiteer) {
		super(theGame, owner, timingNumber, source);
		this.profiteer = profiteer;
	}
	
	@Override
	public void execute(int amount) {
		profiteer.gainCash(amount, this);
	}
}