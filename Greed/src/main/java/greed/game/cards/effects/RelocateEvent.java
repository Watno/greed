package greed.game.cards.effects;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Holding;
import greed.game.eventtypes.WhenPlayEvent;

public class RelocateEvent extends WhenPlayEvent {
    private int markers;

    public RelocateEvent(GreedGame theGame, GreedPlayer owner, int timingNumber, GreedCard source, int markers) {
        super(theGame, owner, timingNumber, source);
        this.markers = markers;
    }

    @Override
    public void execute(GreedGame theGame, GreedCard theCard) {
        if (theCard instanceof Holding) {
            ((Holding) theCard).changeMarkers(markers, this);
        }
    }

    @Override
    public void remove(GreedGame theGame) {
        super.remove(theGame);
        discardActionAfterNextTurnevent(theGame);
    }

}