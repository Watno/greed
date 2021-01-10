package greed.game.cards.effects;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.eventtypes.CashGainAmountModifyEvent;

public class MasterPlanEvent extends CashGainAmountModifyEvent {
    final GreedGame theGame;

    public MasterPlanEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source) {
        super(theGame, owner, timingNumber, source);
        this.theGame = theGame;
    }

    @Override
    public int execute(int amount) {
        theGame.getLogger().doubleAmount(amount * 2, this);
        return amount * 2;
    }

    @Override
    public void remove(GreedGame theGame) {
        super.remove(theGame);
        discardActionAfterNextTurnevent(theGame);
    }
}