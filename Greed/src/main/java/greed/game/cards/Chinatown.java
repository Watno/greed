package greed.game.cards;

import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;

public class Chinatown extends Holding {

    public Chinatown() {
        super(8, "Chinatown", 1, 0, 1);
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
}