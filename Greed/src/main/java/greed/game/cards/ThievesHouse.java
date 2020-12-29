package greed.game.cards;

import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;

public class ThievesHouse extends Holding {

    public ThievesHouse() {
        super(51, "Thieves' House", 1, 0, 1);
    }

    @Override
    public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
        return (thePlayer.getKeys() >= 2);
    }
}