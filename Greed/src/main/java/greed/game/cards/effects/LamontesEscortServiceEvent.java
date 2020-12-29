package greed.game.cards.effects;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.eventtypes.EndOfGameEvent;

public class LamontesEscortServiceEvent extends EndOfGameEvent {
    public LamontesEscortServiceEvent(GreedGame theGame, GreedPlayer profiteer, int timingNumber, GreedCard source) {
        super(theGame, profiteer, timingNumber, source);
    }

    @Override
    public void execute(GreedGame theGame) {
        int numberOfThugs = profiteer.getNumberOfThugs();
        boolean mostThugs = true;
        for (GreedPlayer otherPlayer : theGame.getPlayers()) {
            if (!otherPlayer.equals(profiteer)) {
                if (otherPlayer.getNumberOfThugs() >= numberOfThugs) {
                    mostThugs = false;
                }
            }
        }
        if (mostThugs) {
            profiteer.gainCash(20000, this);
        }
    }
}
