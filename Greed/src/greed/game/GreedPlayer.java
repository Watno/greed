package greed.game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import greed.game.eventtypes.AfterPlayEvent;
import greed.game.eventtypes.CashCostModifyEvent;
import greed.game.eventtypes.CashGainAmountModifyEvent;
import greed.game.eventtypes.CashGainEvent;
import greed.game.eventtypes.IgnoreCostEvent;
import greed.game.eventtypes.IgnoreNeedEvent;
import greed.game.eventtypes.RemoveFromPlayEvent;
import greed.game.eventtypes.WhenPlayEvent;
import greed.meta.GreedConnection;
import greed.meta.RealDecisionMaker;
import greed.meta.ai.CleverDecider;


public class GreedPlayer {
	private DecisionMaker decisionMaker;
	private GreedGame theGame; //TODO: replace passed references to the game with this.
	private String name;
	private int position;
	private int cash=0;
	private int cars=0;
	private int guns=0;
	private int keys=0;
	private int hearts=0;
	private int bottles=0;
	private int wrenches=0;
	private List<GreedCard> hand = Collections.synchronizedList(new ArrayList<GreedCard>());
	private List<GreedCard> draftPile;
	private ArrayList<Holding> holdings = new ArrayList<Holding>();
	private ArrayList<Thug> thugs = new ArrayList<Thug>();
	private ArrayList<Action> actions = new ArrayList<Action>();
	private ArrayList<IgnoreNeedEvent> ignoreNeedEvents = new ArrayList<IgnoreNeedEvent>();
	private ArrayList<IgnoreCostEvent> ignoreCostEvents = new ArrayList<IgnoreCostEvent>();
	private ArrayList<WhenPlayEvent> whenPlayEvents = new ArrayList<WhenPlayEvent>();
	private ArrayList<AfterPlayEvent> afterPlayEvents = new ArrayList<AfterPlayEvent>();
	private ArrayList<CashCostModifyEvent> cashCostModifyEvents = new ArrayList<CashCostModifyEvent>();
	private ArrayList<CashGainAmountModifyEvent> cashGainAmountModifyEvents = new ArrayList<CashGainAmountModifyEvent>();
	private ArrayList<CashGainEvent> cashGainEvents = new ArrayList<CashGainEvent>();
	private ArrayList<RemoveFromPlayEvent> removeFromPlayEvents = new ArrayList<RemoveFromPlayEvent>();

	
	
	GreedPlayer(GreedGame theGame, String name, int position){
		this.theGame = theGame;
		this.name = name;
		this.position = position;
		this.decisionMaker=new CleverDecider(this, theGame);
	}
	
	void draft() {
		int draftIndex;
		do {
			draftIndex = decisionMaker.pickDraftIndex();
		} while (draftIndex<0 || draftIndex>=draftPile.size()); 
		GreedCard draftedCard = draftPile.remove(draftIndex);
		hand.add(draftedCard);
		draftedCard.setLocation(hand);;
	}
	

	public PlayPlan makePlayPlan() {
		if (!hand.isEmpty()) {
			int handIndex;
			do {
				handIndex = decisionMaker.pickHandIndex();
			} while (handIndex<0 || handIndex>=hand.size()); 
			GreedCard theCard = hand.remove(handIndex);//the card still thinks it's in the player's hand at this point, this might be an issue
			return new PlayPlan(theCard, this);
		}
		return null;
	}
	
	
	public boolean payCashCost(int amount, GreedCard payedCard) {
		for (CashCostModifyEvent theEvent : cashCostModifyEvents) {
			amount = theEvent.execute(amount, payedCard);
		}
		if (cash<amount) {
			return false;
		}
		//TODO:Allow to not pay
		changeCash(-amount, " as payment for " + payedCard.getName());
		return true;
	}
	
	public void drawCard(GreedGame theGame) {
		GreedCard theCard = theGame.draw();
		if (theCard!=null) {
			hand.add(theCard);
			theCard.setLocation(hand);
		}
	}
	
	public Thug payThug(GreedGame theGame, GreedCard payedCard) {
		if (!thugs.isEmpty()) {
			int thugIndex;
			do {
				thugIndex = decisionMaker.pickThugIndexOptional("");
			} while (thugIndex<-1 || thugIndex>=thugs.size()); 
			if (thugIndex == -1) {
				return null;
			}
			Thug payedThug = thugs.get(thugIndex);
			payedThug.removeFromPlay(this, theGame, " as payment for " + payedCard.getName());
			return payedThug;
		}
		return null;
	}
	
	public Holding payHolding(GreedGame theGame, GreedCard payedCard) {
		if (!holdings.isEmpty()) {
			int holdingIndex;
			do {
				holdingIndex = decisionMaker.pickHoldingIndexOptional("");
			} while (holdingIndex<-1 || holdingIndex>=holdings.size()); 
			if (holdingIndex == -1) {
				return null;
			}
			Holding payedHolding = holdings.get(holdingIndex);
			payedHolding.removeFromPlay(this, theGame, " as payment for " + payedCard.getName());
			return payedHolding;
		}
		return null;
	}
	
	public GreedCard payHandCard(GreedGame theGame, GreedCard payedCard) {
		if (!hand.isEmpty()) {
			int handIndex;
			do {
				handIndex = decisionMaker.pickHandIndexOptional("");
			} while (handIndex<-1 || handIndex>=hand.size()); 
			if (handIndex == -1) {
				return null;
			}
			GreedCard CardPayedWith = hand.remove(handIndex);
			theGame.addToDiscardPile(CardPayedWith);
			return CardPayedWith;
		}
		return null;
	}
	
	public Thug chooseThug(String reason) {
		if (!thugs.isEmpty()) {
			int thugIndex;
			do {
				thugIndex = decisionMaker.pickThugIndex(reason);
			} while (thugIndex<0 || thugIndex>=thugs.size()); 
			return thugs.get(thugIndex);
		}
		return null;
	}
	
	public Holding chooseHolding(String reason) {
		if (!holdings.isEmpty()) {
			int holdingIndex;
			do {
				holdingIndex = decisionMaker.pickHoldingIndex(reason);
			} while (holdingIndex<0 || holdingIndex>=holdings.size()); 
			return holdings.get(holdingIndex);
		}
		return null;
	}
	
	public void loseThug(GreedGame theGame, String reason) {
		Thug theThug = chooseThug("");
		if(theThug != null) {
			theThug.removeFromPlay(this, theGame, reason);
		}
	}
	
	public void loseHolding(GreedGame theGame, String reason) {
		Holding theHolding = chooseHolding("");
		if(theHolding != null) {
			theHolding.removeFromPlay(this, theGame, reason);
		}
	}
	
	public int calculateScore() {
		int score=cash;
		for (Holding theHolding : holdings) {
			score += theHolding.getMarkers()*10000;
		}
		return score;
	}
	
	public boolean makeYesNoChoice() {
		return decisionMaker.makeYesNoChoice();
	}
	
	public List<GreedCard> getDraftPile() {
		return draftPile;
	}

	void setDraftPile(List<GreedCard> draftPile) {
		this.draftPile = draftPile;
	}
	
	public int getCash() {
		return cash;
	}

	public int getCars() {
		return cars;
	}

	public int getGuns() {
		return guns;
	}

	public int getKeys() {
		return keys;
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
	
	public void gainCash(int amount, String reason) {
		for (CashGainAmountModifyEvent theEvent : cashGainAmountModifyEvents) {
			amount = theEvent.execute(amount);
		}
		changeCash(amount, reason);
		for (CashGainEvent theEvent : cashGainEvents) {
			theEvent.execute(amount);
		}
	}

	public int changeCash(int amount, String reason) {
		if (-amount>=cash) {//can't lose more than you have
			amount=-cash;
		}
		theGame.getLogger().changeCash(this, amount, reason);
		return cash+=amount;
	}
	
	public int changeCars(int amount) {
		return cars+=amount;
	}

	public int changeGuns(int amount) {
		return guns+=amount;
	}

	public int changeKeys(int amount) {
		return keys+=amount;
	}

	public int changeHearts(int amount) {
		return hearts+=amount;
	}

	public int changeBottles(int amount) {
		return bottles+=amount;
	}

	public int changeWrenches(int amount) {
		return wrenches+=amount;
	}

	public List<GreedCard> getHand() {
		return hand;
	}
	
	public void addThug(Thug aThug) {
		thugs.add(aThug);
		aThug.setLocation(thugs);
	}
	
	public void addHolding(Holding aHolding) {
		holdings.add(aHolding);
		aHolding.setLocation(holdings);
	}

	public void addAction(Action anAction) {
		actions.add(anAction);
		anAction.setLocation(actions);
	}

	public ArrayList<Holding> getHoldings() {
		return holdings;
	}
	
	public int getNumberOfHoldings() {
		return holdings.size();
	}



	public ArrayList<Thug> getThugs() {
		return thugs;
	}
	
	public int getNumberOfThugs() {
		return thugs.size();
	}



	public ArrayList<IgnoreNeedEvent> getIgnoreNeedEvents() {
		return ignoreNeedEvents;
	}



	public ArrayList<IgnoreCostEvent> getIgnoreCostEvents() {
		return ignoreCostEvents;
	}



	public ArrayList<WhenPlayEvent> getWhenPlayEvents() {
		return whenPlayEvents;
	}



	public ArrayList<AfterPlayEvent> getAfterPlayEvents() {
		return afterPlayEvents;
	}



	public ArrayList<CashCostModifyEvent> getCashCostModifyEvents() {
		return cashCostModifyEvents;
	}



	public ArrayList<CashGainAmountModifyEvent> getCashGainAmountModifyEvents() {
		return cashGainAmountModifyEvents;
	}



	public ArrayList<CashGainEvent> getCashGainEvents() {
		return cashGainEvents;
	}



	public ArrayList<RemoveFromPlayEvent> getRemoveFromPlayEvents() {
		return removeFromPlayEvents;
	}

	public ArrayList<Action> getActions() {
		return actions;
	}

	public GreedGame getTheGame() {
		return theGame;
	}

	public String getName() {
		return name;
	}

	public void makeReal(GreedConnection connection) {
		this.decisionMaker = new RealDecisionMaker(connection, theGame, this);
		this.name = connection.getName();
	}

	public void send(String gameState) {
		decisionMaker.sendGameState(gameState);
	}

	public int getPosition() {
		return position;
	}
	
	public void replaceByBot() {
		this.decisionMaker = new CleverDecider(this, theGame);//still need to handle currently awaited decision by old decider
	}

}
