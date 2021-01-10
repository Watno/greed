package greed.game.eventtypes;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

import java.util.Collections;

public abstract class CashCostModifyEvent extends TriggeredEvent {
    protected final GreedPlayer owner;

    public CashCostModifyEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source) {
        super(owner.getCashCostModifyEvents(), timingNumber, source);
        this.owner = owner;
    }

    @Override
	public void activate() {
		owner.getCashCostModifyEvents().add(this);
		Collections.sort(owner.getCashCostModifyEvents());	
	}
	
	public int execute(int amount, GreedCard paidCard) {
		return amount;
	}
}
