package greed.game.cards;

import greed.game.*;
import greed.game.eventtypes.TriggeredEvent;

public class EdRubberfaceTeach extends Thug {
	Thug copiedThug = null;
	
	public EdRubberfaceTeach() {
		super(54, "Ed \"Rubberface\" Teach", 0, 0, 0);
	}
	
	@Override
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		if (copiedThug==null) {
			return thePlayer.getNumberOfThugs()>=1;
		}
		return copiedThug.checkNeed(thePlayer, theGame);
	}

	@Override
	public void doRules(GreedPlayer thePlayer, GreedGame theGame, GreedCard executingCard) {
		if (copiedThug==null || copiedThug instanceof EdRubberfaceTeach) {
			copiedThug = thePlayer.chooseThug(this); //due to the need there will always be a thug to copy
			theGame.getLogger().copyCard(this, copiedThug, this);
			//TODO:Can't choose itself, current workaround makes player pick again in this case.
			changeGuns(copiedThug.getBaseGuns());
			changeCars(copiedThug.getBaseCars());
			changeKeys(copiedThug.getBaseKeys());
			//
			timingNumber = copiedThug.getTimingNumber();
			name = copiedThug.getName() + " (copied)";
			for (TriggeredEvent theEvent : copiedThug.getPermanentEffects()) {
				TriggeredEvent copiedEffect = (TriggeredEvent) theEvent.clone();
				copiedEffect.setSource(this);
				addPermanentEffect(theEvent);
			}
			theGame.getLogger().executeRules(thePlayer, this, this);
			copiedThug.doRules(thePlayer, theGame, this);
			theGame.getLogger().unindent();
		}
		else {
			copiedThug.doRules(thePlayer, theGame, this);
		}
	}
	
	@Override 
	public void removeFromPlay(GreedPlayer thePlayer, GreedGame theGame, Reason reason) {
		super.removeFromPlay(thePlayer, theGame, reason);
		name = "Ed \"Rubberface\" Teach";
		timingNumber = 54;
		changeGuns(-getGuns());
		changeCars(-getCars());
		changeKeys(-getKeys());
		copiedThug = null;
	
	}
	
	
}