package greed.game.cards;

import greed.game.*;

public class PeepingTomThumb extends Thug {

    public PeepingTomThumb() {
        super(57, "\"Peeping\" Tom \"Thumb\"", 0, 2, 0);
    }


    @Override
    public boolean payCost(GreedPlayer thePlayer, GreedGame theGame) {
        return thePlayer.payHolding(this) != null;
    }

    @Override
    public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
        int mostMarkers = 0;
        for (GreedPlayer anyPlayer : theGame.getPlayers()) {
            for (Holding theHolding : anyPlayer.getHoldings()) {
                mostMarkers = Math.max(mostMarkers, theHolding.getMarkers());
            }
        }
        thePlayer.gainCash(mostMarkers * 5000, executingCard);
    }
}