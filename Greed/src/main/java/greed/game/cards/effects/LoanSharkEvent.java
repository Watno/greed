package greed.game.cards.effects;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;
import greed.game.eventtypes.WhenPlayEvent;

public class LoanSharkEvent extends WhenPlayEvent {

    public LoanSharkEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source) {
        super(theGame, owner, timingNumber, source);
    }

    @Override
    public void execute(GreedGame theGame, GreedCard theCard) {
        if (theCard instanceof Holding) {
            ((Holding) theCard).changeMarkers(-1, this);
        }
    }
}