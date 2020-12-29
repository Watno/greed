package greed.game.cards;

import greed.game.Action;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;

public class Arson extends Action {

    public Arson() {
        super(75, "Arson!");
    }


    @Override
    public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
        int amount = -thePlayer.getNumberOfThugs() * 10000;
        for (GreedPlayer otherPlayer : theGame.getPlayers()) {
            if (!otherPlayer.equals(thePlayer)) {
                otherPlayer.changeCash(amount, executingCard);
            }
        }
    }

}