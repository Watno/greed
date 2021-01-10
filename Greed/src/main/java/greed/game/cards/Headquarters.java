package greed.game.cards;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;

public class Headquarters extends Holding {

    public Headquarters() {
        super(37, "Headquarters", 0, 0, 0);
    }

    @Override
    public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
        return (thePlayer.getGuns() >= 1 && thePlayer.getKeys() >= 1);
    }

    @Override
    public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
        changeMarkers(thePlayer.getNumberOfHoldings(), executingCard);
    }
}
