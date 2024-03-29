package greed.game.cards;

import greed.game.Action;
import greed.game.GreedCard;
import greed.game.GreedGame;
import greed.game.GreedPlayer;


public class Gambit extends Action {

    public Gambit() {
        super(27, "Gambit!");
    }

    @Override
    public boolean payCost(GreedPlayer thePlayer, GreedGame theGame) {
        return (thePlayer.payHandCard(this) != null);
    }

    @Override
    public boolean isPlayable(GreedPlayer thePlayer, GreedGame theGame) {
        super.isPlayable(thePlayer, theGame);
        return thePlayer.getHand().size() >= 1;
    }

    @Override
    public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
        thePlayer.gainCash(30000, executingCard);
    }


}