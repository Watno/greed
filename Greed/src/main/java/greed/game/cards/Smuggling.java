package greed.game.cards;

import greed.game.Action;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

public class Smuggling extends Action {

    public Smuggling() {
        super(32, "Smuggling!");
    }

    @Override
    public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
        return (thePlayer.getCars() >= 1 && thePlayer.getBottles() >= 1);
    }

    @Override
    public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
        thePlayer.gainCash(25000, executingCard);
    }
}