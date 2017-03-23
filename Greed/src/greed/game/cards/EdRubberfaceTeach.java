package greed.game.cards;

import greed.game.GreedGame;
import greed.game.GreedPlayer;
import greed.game.Thug;
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
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		if (copiedThug==null) {
			copiedThug = thePlayer.chooseThug(""); //due to the need there will always be a thug to copy
			changeGuns(copiedThug.getGuns());
			changeCars(copiedThug.getCars());
			changeKeys(copiedThug.getKeys());
			//TOFO: fix interaction with TedNapoleonBonham
			timingNumber = copiedThug.getTimingNumber();
			name = copiedThug.getName() + "(copied)";
			for (TriggeredEvent theEvent : copiedThug.getPermanentEffects()) {
				TriggeredEvent copiedEffect = (TriggeredEvent) theEvent.clone();
				copiedEffect.setSource(this);
				addPermanentEffect(theEvent);
			}
		}
		copiedThug.doRules(thePlayer, theGame);
	}
	
	@Override 
	public void removeFromPlay(GreedPlayer thePlayer, GreedGame theGame, String reason) {
		super.removeFromPlay(thePlayer, theGame, reason);
		name = "Ed \"Rubberface\" Teach";
		timingNumber = 54;
		changeGuns(-copiedThug.getGuns());
		changeCars(-copiedThug.getCars());
		changeKeys(-copiedThug.getKeys());
		copiedThug = null;
	
	}
	
	
}