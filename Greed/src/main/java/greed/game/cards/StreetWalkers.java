package greed.game.cards;

import greed.game.*;

public class StreetWalkers extends Action {

    public StreetWalkers() {
        super(39, "Street walkers!");
    }

    @Override
    public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
        return (thePlayer.getHearts() >= 1);
    }

    @Override
    public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
        for (Holding theHolding : thePlayer.getHoldings()) {
            theHolding.changeMarkers(1, executingCard);
        }
    }
}
