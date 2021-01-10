package greed.game.cards.effects;

import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.eventtypes.EachTurnEvent;

public class DollsOnCallEvent extends EachTurnEvent {
    public DollsOnCallEvent(GreedGame theGame, GreedPlayer profiteer, int timingNumber, GreedCard source) {
        super(theGame, profiteer, timingNumber, source);
    }

    @Override
    public void execute(GreedGame theGame) {
        int mostHoldings = 0;
        boolean tied = false;
        GreedPlayer ownerOfMostHoldings = null;
        for (GreedPlayer aPlayer : theGame.getPlayers()) {
            int aPlayersNumberOfHoldings = aPlayer.getNumberOfHoldings();
            if (aPlayersNumberOfHoldings == mostHoldings) {
                tied = true;
            }
            if (aPlayersNumberOfHoldings > mostHoldings) {
                mostHoldings = aPlayersNumberOfHoldings;
                tied = false;
                ownerOfMostHoldings = aPlayer;
            }
        }
        if (!tied) {
            ownerOfMostHoldings.gainCash(5000, this);
        }

    }
}
