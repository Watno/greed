package greed.game.cards.effects;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.eventtypes.EachTurnEvent;

public class JennysWaterfrontDiveEvent extends EachTurnEvent {

    public JennysWaterfrontDiveEvent(GreedGame theGame, GreedPlayer profiteer, int timingNumber, GreedCard source) {
        super(theGame, profiteer, timingNumber, source);
    }

    @Override
    public void execute(GreedGame theGame) {
        boolean noBottlesOrHearts = true;
        for (GreedPlayer aPlayer : theGame.getPlayers()) {
            if (aPlayer.getBottles() >= 1 || aPlayer.getHearts() >= 1) {
                noBottlesOrHearts = false;
                break;
            }
        }
        if (noBottlesOrHearts) {
            profiteer.gainCash(10000, this);
        }
    }


}