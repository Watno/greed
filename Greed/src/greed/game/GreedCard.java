package greed.game;

import java.util.ArrayList;
import java.util.List;

import greed.game.eventtypes.AfterPlayEvent;
import greed.game.eventtypes.RemoveFromPlayEvent;
import greed.game.eventtypes.TriggeredEvent;
import greed.game.eventtypes.WhenPlayEvent;


public abstract class GreedCard {
	protected int timingNumber;
	protected String name;
	private int turnPlayed=-1;
	private ArrayList<TriggeredEvent> permanentEffects = new ArrayList<TriggeredEvent>();
	private List <? extends GreedCard> location;
	protected GreedPlayer owner = null;
	
	
	GreedCard(int timingNumber, String name){
		this.timingNumber=timingNumber;
		this.name=name;
		
	}

	void tryToPlay(GreedPlayer thePlayer, GreedGame theGame) {
		if (checkPrerequisites(thePlayer, theGame)) {
			play(thePlayer, theGame);
		}
		else {
			theGame.addToDiscardPile(this);
		}
	}
	public boolean isPlayable(GreedPlayer thePlayer, GreedGame theGame) {
		return checkNeed(thePlayer, theGame);
	}
	
	protected boolean checkPrerequisites(GreedPlayer thePlayer, GreedGame theGame){
		boolean allowPlay = true;
		if (thePlayer.getIgnoreCostEvents().size()==0) {
			allowPlay &= payCost(thePlayer, theGame);
		}
		if (thePlayer.getIgnoreNeedEvents().size()==0) {
			allowPlay &= checkNeed(thePlayer, theGame);
		}
		if (allowPlay == false) {
			theGame.getLogger().cantAffordCard(thePlayer, this);
		}
		return allowPlay;
	}
	
	public boolean checkNeed(GreedPlayer thePlayer, GreedGame theGame) {
		return true;
	}
	
	protected boolean payCost(GreedPlayer thePlayer, GreedGame theGame) {
		return true;
	}
	
	public void play(GreedPlayer thePlayer, GreedGame theGame) {
		theGame.getLogger().playCard(thePlayer, this);
		putInPlay(thePlayer, theGame);
		for (WhenPlayEvent anEvent : thePlayer.getWhenPlayEvents()) {
			anEvent.execute(theGame, this);
		}
		doRules(thePlayer, theGame);
		for (AfterPlayEvent anEvent : thePlayer.getAfterPlayEvents()) {
			anEvent.execute(theGame, this);
		}
		theGame.getLogger().unindent();
	}
	
	protected void putInPlay(GreedPlayer thePlayer, GreedGame theGame) {
		turnPlayed = theGame.getTurnCounter();
		owner = thePlayer;
	}
	
	
	public void removeFromPlay(GreedPlayer thePlayer, GreedGame theGame, String reason) {
		if (!(this instanceof Action)) {//this check (and the one below are not nice, needs refactoring...
			theGame.getLogger().loseCard(thePlayer, this, reason);
			for (RemoveFromPlayEvent anEvent : thePlayer.getRemoveFromPlayEvents()) {
				anEvent.execute(theGame, this);
			}
			for (TriggeredEvent anEvent : permanentEffects) {
				anEvent.remove(theGame);
			}
		}
		turnPlayed = -1;
		owner = null;
		theGame.addToDiscardPile(this);
		if (!(this instanceof Action)) {
			theGame.getLogger().unindent();
		}
	}
	
	public void doRules(GreedPlayer thePlayer, GreedGame theGame) {
		
	}
	
	public int getTimingNumber() {
		return timingNumber;
	}

	public int getTurnPlayed() {
		return turnPlayed;
	}
	
	public void addPermanentEffect(TriggeredEvent effect) {
		permanentEffects.add(effect);
		effect.activate();
	}

	public List<? extends GreedCard> getLocation() {
		return location;
	}

	public void setLocation(List<? extends GreedCard> location) {
		this.location = location;
	}

	public ArrayList<TriggeredEvent> getPermanentEffects() {
		return permanentEffects;
	}

	public String getName() {
		return name;
	}


}
