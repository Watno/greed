package greed.game.cards;

import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;
import greed.game.cards.effects.BiscuitsOMalleyEvent;

public class BiscuitsOMalley extends Thug {

    public BiscuitsOMalley() {
        super(6, "\"Biscuits\" O'Malley", 0, 0, 0);
    }


    @Override
    protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
        super.putInPlay(thePlayer, theGame);
        addPermanentEffect(new BiscuitsOMalleyEvent(theGame, thePlayer, timingNumber, this));
    }
}