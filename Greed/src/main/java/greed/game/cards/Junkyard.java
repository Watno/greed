package greed.game.cards;

import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;
import greed.game.cards.effects.JunkyardEvent;

public class Junkyard extends Holding {

    public Junkyard() {
        super(12, "Junkyard", 0, 0, 1);
    }

    @Override
    public boolean payCost(GreedPlayer thePlayer, GreedGame theGame) {
        return thePlayer.payCashCost(10000, this);
    }

    @Override
    public boolean isPlayable(GreedPlayer thePlayer, GreedGame theGame) {
        super.isPlayable(thePlayer, theGame);
        return thePlayer.getCash() >= 10000;
    }

    @Override
    public void placeMarkers(GreedPlayer thePlayer) {

    }

    @Override
    protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
        super.putInPlay(thePlayer, theGame);
        addPermanentEffect(new JunkyardEvent(theGame, thePlayer, timingNumber, this));
    }
}
