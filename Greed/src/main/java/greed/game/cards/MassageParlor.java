package greed.game.cards;

import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;

public class MassageParlor extends Holding {

    public MassageParlor() {
        super(48, "Massage Parlor", 0, 1, 1);
    }

    @Override
    public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
        return (thePlayer.getCars() >= 2);
    }
}
