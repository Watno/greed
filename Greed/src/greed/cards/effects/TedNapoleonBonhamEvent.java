package greed.cards.effects;

import greed.GreedCard;
import greed.GreedGame;
import greed.Thug;
import greed.eventypes.TriggeredEvent;

public class TedNapoleonBonhamEvent extends TriggeredEvent{
	
	public TedNapoleonBonhamEvent(GreedGame theGame, int timingNumber, GreedCard source) {
		super(null, timingNumber, source);
	}
	
	@Override
	public void activate() {
		if (source instanceof Thug) {
			Thug sourceAsThug = (Thug) source;
			sourceAsThug.changeGuns(1);
			sourceAsThug.changeKeys(1);		
		}
	}
	
	@Override
	public void remove(GreedGame theGame) {
		if (source instanceof Thug) {
			Thug sourceAsThug = (Thug) source;
			sourceAsThug.changeGuns(-1);
			sourceAsThug.changeKeys(-1);		
		}
	}
}
	