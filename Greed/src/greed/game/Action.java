package greed.game;

public abstract class Action extends GreedCard{
	
	public Action(int timingNumber, String name){
		super(timingNumber, name);	
	}
	
	@Override
	public void play(GreedPlayer thePlayer, GreedGame theGame) {
		super.play(thePlayer, theGame);
		if(getLocation()==thePlayer.getActions()) {
			discardIfNoNextTurnEffect(thePlayer, theGame);
		}
	}
	
	protected void discardIfNoNextTurnEffect(GreedPlayer thePlayer, GreedGame theGame) {
		removeFromPlay(thePlayer, theGame, "");
	}
	//Override this with empty if setting up a next turn effect, and call discardAfterNextTurnEvent when the next turn effect is removed
	
	public void discardAfterNextTurnEvent(GreedGame theGame) {
		if (getTurnPlayed() == theGame.getTurnCounter()-1 && getLocation()==owner.getActions()) {
			removeFromPlay(owner, theGame, "");
		}
	}
	
	@Override
	protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
		super.putInPlay(thePlayer, theGame);
		thePlayer.addAction(this);
	}
	
	@Override
	public void removeFromPlay(GreedPlayer thePlayer, GreedGame theGame, String reason) {
		super.removeFromPlay(thePlayer, theGame, reason);
		thePlayer.getActions().remove(this);
		theGame.addToDiscardPile(this);
	}
}
