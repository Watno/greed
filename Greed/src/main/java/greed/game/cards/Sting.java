package greed.game.cards;

import greed.game.Action;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

public class Sting extends Action {

    public Sting() {
        super(33, "Sting!");
    }


    @Override
    public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
        thePlayer.gainCash(thePlayer.getCars() * 10000, executingCard);
        thePlayer.gainCash(thePlayer.getHearts() * 10000, executingCard);
    }

}