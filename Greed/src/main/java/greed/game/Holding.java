package greed.game;


public abstract class Holding extends GreedCard {
    private final int hearts;
    private final int bottles;
    private final int wrenches;
    private int markers = 0;
    private int markersBeforeRemove = 0;

    public Holding(int timingNumber, String name, int bottles, int hearts, int wrenches) {
        super(timingNumber, name);
        this.hearts = hearts;
        this.bottles = bottles;
        this.wrenches = wrenches;
    }
	
	@Override
	protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
		super.putInPlay(thePlayer, theGame);
		thePlayer.addHolding(this);
		thePlayer.changeHearts(hearts);
		thePlayer.changeBottles(bottles);
		thePlayer.changeWrenches(wrenches);
		placeMarkers(thePlayer);
	}
	
	public void placeMarkers(GreedPlayer thePlayer) {
		placeMarkersForReal(thePlayer);
	}
	
	public void placeMarkersForReal(GreedPlayer thePlayer) {
		int amount=0;
		if (hearts>=1) {
			amount+=thePlayer.getHearts();
		}
		if (bottles>=1) {
			amount+=thePlayer.getBottles();
		}
		if (wrenches>=1) {
			amount+=thePlayer.getWrenches();
		}
		changeMarkers(amount, new IconReason());
	}
	
	
	@Override
	public void removeFromPlay(GreedPlayer thePlayer, GreedGame theGame, Reason reason) {
		super.removeFromPlay(thePlayer, theGame, reason);
		thePlayer.getHoldings().remove(this);
		markersBeforeRemove = markers;
		markers = 0;
		thePlayer.changeHearts(-hearts);
		thePlayer.changeBottles(-bottles);
		thePlayer.changeWrenches(-wrenches);
	}
	
	public int changeMarkers(int amount, Reason reason) {
		if (-amount>=markers) {//can't lose more than you have
			amount=-markers;
		}
		owner.getTheGame().getLogger().placeMarkers(owner, amount, this, reason);
		return markers+=amount;
	}

	public int getHearts() {
		return hearts;
	}

	public int getBottles() {
		return bottles;
	}

	public int getWrenches() {
		return wrenches;
	}

	public int getMarkers() {
		return markers;
	}

	public int getMarkersBeforeRemove() {
		return markersBeforeRemove;
	}
}
