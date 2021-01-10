package greed.game;

public class PlayPlan implements Comparable<PlayPlan> {
    private final GreedCard theCard;
    private final GreedPlayer thePlayer;

    PlayPlan(GreedCard theCard, GreedPlayer thePlayer) {
        this.theCard = theCard;
        this.thePlayer = thePlayer;
    }

    @Override
    public int compareTo(PlayPlan otherPlan) {
        return theCard.getTimingNumber() - otherPlan.getTheCard().getTimingNumber();
    }
	
	public void execute(GreedGame theGame) {
		theCard.tryToPlay(thePlayer, theGame);
	}

	GreedCard getTheCard() {
		return theCard;
	}

	GreedPlayer getThePlayer() {
		return thePlayer;
	}
	
}
