package greed.game.cards;

import greed.game.*;


public class Seance extends Action {

    public Seance() {
        super(80, "Seance!");
    }


    @Override
    public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
        thePlayer.gainCash(10000, executingCard);
        Holding theHolding = thePlayer.chooseHolding(this);
        if (theHolding != null) {
            theHolding.changeMarkers(1, executingCard);
        }
        if (thePlayer.makeYesNoChoice(this)) {
            PlayPlan playPlan = thePlayer.makePlayPlan();
            if (playPlan != null) {
                playPlan.execute(theGame);
            }
        }

    }


}