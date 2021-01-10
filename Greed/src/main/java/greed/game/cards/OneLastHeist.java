package greed.game.cards;

import greed.game.Action;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.cards.effects.OneLastHeistEvent;

public class OneLastHeist extends Action {

    public OneLastHeist() {
        super(73, "One last heist!");
    }

    @Override
    public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
        return thePlayer.getGuns() >= 1;
    }

    @Override
    public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
        int mostThugs = 0;
        for (GreedPlayer anyPlayer : theGame.getPlayers()) {
            mostThugs = Math.max(mostThugs, anyPlayer.getNumberOfThugs());
        }
        thePlayer.gainCash(mostThugs * 5000, executingCard);
        theGame.addNextTurnEvent(new OneLastHeistEvent(theGame, thePlayer, timingNumber, executingCard));
    }

    @Override
    protected void discardIfNoNextTurnEffect(GreedPlayer thePlayer, GreedGame theGame) {

    }

}