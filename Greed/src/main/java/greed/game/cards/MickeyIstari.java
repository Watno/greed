package greed.game.cards;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;

public class MickeyIstari extends Thug {

    public MickeyIstari() {
        super(25, "Mickey Istari", 1, 0, 0);
    }

    @Override
    public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
        thePlayer.gainCash(thePlayer.getBottles() * 10000, executingCard);
    }
}
