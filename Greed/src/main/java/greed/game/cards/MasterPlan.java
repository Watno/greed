package greed.game.cards;

import greed.game.Action;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.cards.effects.MasterPlanEvent;

public class MasterPlan extends Action {

    public MasterPlan() {
        super(78, "Master Plan!");
    }


    @Override
    public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
        theGame.addNextTurnEvent(new MasterPlanEvent(theGame, thePlayer, timingNumber, executingCard));
    }

    @Override
    protected void discardIfNoNextTurnEffect(GreedPlayer thePlayer, GreedGame theGame) {

    }
}