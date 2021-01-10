package greed.game.cards;

import greed.game.Action;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

public class CircusOfCrime extends Action {

    public CircusOfCrime() {
        super(26, "Circus of crime!");
    }

    @Override
    public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
        return (thePlayer.getNumberOfHoldings() >= 2);
    }

    @Override
    public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
        thePlayer.gainCash(thePlayer.getNumberOfThugs() * 10000, executingCard);
    }
}