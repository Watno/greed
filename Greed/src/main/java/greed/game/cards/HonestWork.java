package greed.game.cards;

import greed.game.Action;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

public class HonestWork extends Action {

    public HonestWork() {
        super(36, "Honest work!");
    }

    @Override
    public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
        thePlayer.gainCash(15000, executingCard);
        if (thePlayer.getCash() < 50000) {
            putBackInHand(thePlayer, theGame, executingCard, thePlayer.getActions());
        }
    }
}