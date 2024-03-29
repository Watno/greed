package greed.game.cards;

import greed.game.*;

public class Liquidate extends Action {
    private int markers = 0;

    public Liquidate() {
        super(4, "Liquidate!");
    }

    @Override
    public boolean payCost(GreedPlayer thePlayer, GreedGame theGame) {
        Holding theHolding = thePlayer.payHolding(this);
        if (theHolding != null) {
            markers = theHolding.getMarkersBeforeRemove();
            return true;
        }
        return false;
    }

    @Override
    public boolean isPlayable(GreedPlayer thePlayer, GreedGame theGame) {
        super.isPlayable(thePlayer, theGame);
        return thePlayer.getNumberOfHoldings() >= 1;
    }

    @Override
    public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
        thePlayer.gainCash(markers * 15000, executingCard);
    }


}