package greed.game.cards.effects;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;
import greed.game.eventtypes.EachTurnEvent;

public class SexySadiesEvent extends EachTurnEvent {

    public SexySadiesEvent(GreedGame theGame, GreedPlayer profiteer, GreedCard source, int timingNumber) {
        super(theGame, profiteer, timingNumber, source);
    }

    @Override
    public void execute(GreedGame theGame) {
        if (source instanceof Holding) {
            if (((Holding) source).getMarkers() >= 3) {
                profiteer.gainCash(5000, this);
            }
        }
    }
}