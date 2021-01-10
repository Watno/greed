package greed.game.cards;

import greed.game.*;

public class Renovate extends Action {

    public Renovate() {
        super(38, "Renovate!");
    }

    @Override
    public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
        Holding theHolding = thePlayer.chooseHolding(this);
        if (theHolding != null) {
            theHolding.changeMarkers(2, executingCard);
        }
    }


}