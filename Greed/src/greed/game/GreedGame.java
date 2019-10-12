package greed.game;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import greed.game.cards.Arson;
import greed.game.cards.BeggarsBanquet;
import greed.game.cards.BiscuitsOMalley;
import greed.game.cards.BobbyCorduroyBrown;
import greed.game.cards.BookieJoint;
import greed.game.cards.Chinatown;
import greed.game.cards.CircusOfCrime;
import greed.game.cards.ComplexScheme;
import greed.game.cards.DaisysCookies;
import greed.game.cards.DickieFlushDiamond;
import greed.game.cards.DollsOnCall;
import greed.game.cards.EdCheeseClothMcGinty;
import greed.game.cards.EdRubberfaceTeach;
import greed.game.cards.EstateHeist;
import greed.game.cards.EugeneTheButcherMidge;
import greed.game.cards.FriendlyGusCaspar;
import greed.game.cards.Gambit;
import greed.game.cards.GenerousJennyJones;
import greed.game.cards.HalloweenJackParis;
import greed.game.cards.HarveyBrainsRatcliffe;
import greed.game.cards.Headquarters;
import greed.game.cards.Hideout;
import greed.game.cards.HonestWork;
import greed.game.cards.Inform;
import greed.game.cards.InsiderTrading;
import greed.game.cards.InsuranceOffice;
import greed.game.cards.InsuranceScam;
import greed.game.cards.JackCrackerThompson;
import greed.game.cards.JennysWaterfrontDive;
import greed.game.cards.JoesGinJoint;
import greed.game.cards.Junkyard;
import greed.game.cards.KingRichardTheThird;
import greed.game.cards.KrazyKatClub;
import greed.game.cards.LamontesEscortService;
import greed.game.cards.Liquidate;
import greed.game.cards.LoanShark;
import greed.game.cards.LouieSavoirOFarrell;
import greed.game.cards.MassageParlor;
import greed.game.cards.MasterPlan;
import greed.game.cards.MickeyIstari;
import greed.game.cards.MorticiasAbsintheParlor;
import greed.game.cards.MuseumHeist;
import greed.game.cards.NataschaTheSquirrelRubin;
import greed.game.cards.NothingBeatsRockBenson;
import greed.game.cards.OneLastHeist;
import greed.game.cards.PaddysPub;
import greed.game.cards.PeepingTomThumb;
import greed.game.cards.PeteRepeatFell;
import greed.game.cards.PickpocketNetwork;
import greed.game.cards.PolycephalusPatriciaJones;
import greed.game.cards.PoorHouse;
import greed.game.cards.ProtectionRacket;
import greed.game.cards.Raid;
import greed.game.cards.RandomScrubPatterson;
import greed.game.cards.Relocate;
import greed.game.cards.Renovate;
import greed.game.cards.RottenJohnnySimmons;
import greed.game.cards.SandysSnookerNSchnaps;
import greed.game.cards.Scouting;
import greed.game.cards.Seance;
import greed.game.cards.SexySadies;
import greed.game.cards.Shakedown;
import greed.game.cards.SixCorners;
import greed.game.cards.Smuggling;
import greed.game.cards.StealIdeas;
import greed.game.cards.Sting;
import greed.game.cards.StingyStanMcDowell;
import greed.game.cards.StreetWalkers;
import greed.game.cards.SuckerConvention;
import greed.game.cards.SuicideMission;
import greed.game.cards.TakeCareOfBusiness;
import greed.game.cards.TedNapoleonBonham;
import greed.game.cards.TheRitz;
import greed.game.cards.ThievesHouse;
import greed.game.cards.TommysGunsNAmmo;
import greed.game.cards.TrotskysBurlesque;
import greed.game.cards.Vandalism;
import greed.game.cards.ViciousSydVarney;
import greed.game.cards.WolfgangButtercup;
import greed.game.cards.ZoningOffice;
import greed.game.eventtypes.AfterPlaysResolveEvent;
import greed.game.eventtypes.EachTurnEvent;
import greed.game.eventtypes.EndOfGameEvent;
import greed.game.eventtypes.EndOfTurnEvent;
import greed.game.eventtypes.TriggeredEvent;
import greed.meta.JSONGenerator;
import greed.meta.Logger;
import server.IUserFromGamePerspective;



public class GreedGame {
	private int turnCounter=0;
	private ArrayList<GreedPlayer> players = new ArrayList<GreedPlayer>();
	private ArrayList<GreedCard> drawPile = new ArrayList<GreedCard>();
	private ArrayList<GreedCard> discardPile = new ArrayList<GreedCard>();
	private ArrayList<AfterPlaysResolveEvent> afterPlaysResolveEvents = new ArrayList<AfterPlaysResolveEvent>();
	private ArrayList<EachTurnEvent> eachTurnEvents = new ArrayList<EachTurnEvent>();
	private ArrayList<EndOfTurnEvent> endOfTurnEvents = new ArrayList<EndOfTurnEvent>();
	private ArrayList<EndOfGameEvent> endOfGameEvents = new ArrayList<EndOfGameEvent>();
	private ArrayList<TriggeredEvent> thisTurnEvents = new ArrayList<TriggeredEvent>();
	private ArrayList<TriggeredEvent> nextTurnEvents = new ArrayList<TriggeredEvent>();
	private Logger logger = new Logger();
	private int realPlayers = 0;
	
	public GreedGame(int numberPlayers) {
		for (int i=0; i<numberPlayers; i++) {
			GreedPlayer aPlayer = new GreedPlayer(this, "Player "+ (i+1), i );
			players.add(aPlayer);
		}
	}
		
	public void startGame() {
		System.out.println(LocalDateTime.now() +" - " +"Game started");
		initialize();
		while (turnCounter<12) {
			executeTurn();
		}
		logger.announceGameEnd();
		for (EndOfGameEvent theEvent: endOfGameEvents) {
			theEvent.execute(this);
		}
		for (GreedPlayer thePlayer: players) {
			int score = thePlayer.calculateScore();
			logger.reportScore(thePlayer, score);
		}
		sendGameState();
	}
	
	public void sendGameState() {
		JSONGenerator.generateJSON(this);
	}
	
	public void addRealPlayer(IUserFromGamePerspective connection) {
		players.get(realPlayers).makeReal(connection);
		realPlayers++;
	}
	
	private void initialize(){
		Collections.shuffle(players);
		generateDrawPile();
		Collections.shuffle(drawPile);
		for (GreedPlayer thePlayer: players) {
			thePlayer.setDraftPile(buildDraftPile());
		}	
	}
	
	private void executeTurn() {
		turnCounter++;
		logger.turnStart(turnCounter);
		thisTurnEvents = nextTurnEvents;
		nextTurnEvents = new ArrayList<TriggeredEvent>();
		for (TriggeredEvent theEvent: thisTurnEvents) {
			logger.activateEffect(theEvent);
			theEvent.activate();
		}
		List<PlayPlan> playPlans = Collections.synchronizedList(new ArrayList<PlayPlan>());
		List<Thread> threads = new ArrayList<Thread>();
		for (GreedPlayer thePlayer: players) {
			Thread theThread = new Thread (new DraftPlayThread(turnCounter, thePlayer, playPlans, this));
			threads.add(theThread);
			theThread.start();
		}
		for (Thread theThread : threads) {
			try {
				theThread.join();
			}
			catch(Exception e) {	
				e.printStackTrace();
			}
		}
		Collections.sort(playPlans);
		for (PlayPlan thePlan: playPlans) {
			thePlan.execute(this);
		}
		for (AfterPlaysResolveEvent theEvent: afterPlaysResolveEvents) {
			theEvent.execute(this);
		}
		for (EachTurnEvent theEvent: eachTurnEvents) {
			theEvent.execute(this);
		}
		for (EndOfTurnEvent theEvent: endOfTurnEvents) {
			theEvent.execute(this);
		}
		for (TriggeredEvent theEvent: thisTurnEvents) {
			theEvent.remove(this);
		}
		passCards();
	}
	
	private void passCards() {
		List<GreedCard> toPass = players.get(players.size()-1).getDraftPile();
		for (GreedPlayer thePlayer: players) {
			List<GreedCard> toReceive = toPass;
			toPass = thePlayer.getDraftPile();
			thePlayer.setDraftPile(toReceive);
		}
	}
	
	private List<GreedCard> buildDraftPile() {
		List<GreedCard> draftPile = Collections.synchronizedList(new ArrayList<GreedCard>());
		for(int i=0; i<12; i++) {
			GreedCard theCard = draw();//there are always cards in the draw piel at this stage
			draftPile.add(theCard);
			theCard.setLocation(draftPile);
		}
		return draftPile;
	}
	
	public GreedCard draw() {
		if (drawPile.size()>0) {
			return drawPile.remove(drawPile.size()-1);
		}
		else return null;
	}
	
	private void addToDrawPile(GreedCard aCard) {
		drawPile.add(aCard);
		aCard.setLocation(drawPile);
	}

	public void addToDiscardPile(GreedCard aCard) {
		discardPile.add(aCard);
		aCard.setLocation(discardPile);
	}
	
	public ArrayList<GreedPlayer> getPlayers() {
		return players;
	}

	public int getTurnCounter() {
		return turnCounter;
	}

	public ArrayList<EachTurnEvent> getEachTurnEvents() {
		return eachTurnEvents;
	}
	
	public ArrayList<EndOfTurnEvent> getEndOfTurnEvents() {
		return endOfTurnEvents;
	}
	
	
	public ArrayList<EndOfGameEvent> getEndOfGameEvents() {
		return endOfGameEvents;
	}
	
	public void addThisTurnEvent(TriggeredEvent theEvent) {
		thisTurnEvents.add(theEvent);
		theEvent.activate();
	}
	
	public void addNextTurnEvent(TriggeredEvent theEvent) {
		nextTurnEvents.add(theEvent);
	}
	
	private void  generateDrawPile() {
		addToDrawPile(new Arson());
		addToDrawPile(new BeggarsBanquet());
		addToDrawPile(new BiscuitsOMalley());
		addToDrawPile(new BobbyCorduroyBrown());
		addToDrawPile(new BookieJoint());
		addToDrawPile(new Chinatown());
		addToDrawPile(new CircusOfCrime());
		addToDrawPile(new ComplexScheme());
		addToDrawPile(new DaisysCookies());
		addToDrawPile(new DickieFlushDiamond());
		addToDrawPile(new DollsOnCall());
		addToDrawPile(new EdCheeseClothMcGinty());
		addToDrawPile(new EdRubberfaceTeach());
		addToDrawPile(new EstateHeist());
		addToDrawPile(new EugeneTheButcherMidge());
		addToDrawPile(new FriendlyGusCaspar());
		addToDrawPile(new Gambit());
		addToDrawPile(new GenerousJennyJones());	
		addToDrawPile(new HalloweenJackParis());	
		addToDrawPile(new HarveyBrainsRatcliffe());
		addToDrawPile(new Headquarters());
		addToDrawPile(new Hideout());
		addToDrawPile(new HonestWork());
		addToDrawPile(new Inform());
		addToDrawPile(new InsiderTrading());
		addToDrawPile(new InsuranceOffice());
		addToDrawPile(new InsuranceScam());
		addToDrawPile(new JackCrackerThompson());
		addToDrawPile(new JennysWaterfrontDive());
		addToDrawPile(new JoesGinJoint());
		addToDrawPile(new Junkyard());
		addToDrawPile(new KingRichardTheThird());
		addToDrawPile(new KrazyKatClub());
		addToDrawPile(new LamontesEscortService());
		addToDrawPile(new Liquidate());
		addToDrawPile(new LoanShark());
		addToDrawPile(new LouieSavoirOFarrell());
		addToDrawPile(new MassageParlor());
		addToDrawPile(new MasterPlan());
		addToDrawPile(new MickeyIstari());
		addToDrawPile(new MorticiasAbsintheParlor());
		addToDrawPile(new MuseumHeist());
		addToDrawPile(new NataschaTheSquirrelRubin());
		addToDrawPile(new NothingBeatsRockBenson());
		addToDrawPile(new OneLastHeist());
		addToDrawPile(new PaddysPub());
		addToDrawPile(new PeepingTomThumb());
		addToDrawPile(new PeteRepeatFell());
		addToDrawPile(new PickpocketNetwork());
		addToDrawPile(new PolycephalusPatriciaJones());
		addToDrawPile(new PoorHouse());
		addToDrawPile(new ProtectionRacket());
		addToDrawPile(new Raid());
		addToDrawPile(new RandomScrubPatterson());
		addToDrawPile(new Relocate());
		addToDrawPile(new Renovate());
		addToDrawPile(new RottenJohnnySimmons());
		addToDrawPile(new SandysSnookerNSchnaps());
		addToDrawPile(new Scouting());
		addToDrawPile(new Seance());
		addToDrawPile(new SexySadies());
		addToDrawPile(new Shakedown());
		addToDrawPile(new SixCorners());
		addToDrawPile(new Smuggling());
		addToDrawPile(new StealIdeas());
		addToDrawPile(new Sting());
		addToDrawPile(new StingyStanMcDowell());
		addToDrawPile(new StreetWalkers());
		addToDrawPile(new SuckerConvention());
		addToDrawPile(new SuicideMission());	
		addToDrawPile(new TakeCareOfBusiness());
		addToDrawPile(new TedNapoleonBonham());
		addToDrawPile(new TheRitz());
		addToDrawPile(new ThievesHouse());
		addToDrawPile(new TommysGunsNAmmo());
		addToDrawPile(new TrotskysBurlesque());
		addToDrawPile(new Vandalism());
		addToDrawPile(new ViciousSydVarney());
		addToDrawPile(new WolfgangButtercup());
		addToDrawPile(new ZoningOffice());		
	}

	public ArrayList<AfterPlaysResolveEvent> getAfterPlaysResolveEvents() {
		return afterPlaysResolveEvents;
	}

	public ArrayList<GreedCard> getDiscardPile() {
		return discardPile;
	}

	public Logger getLogger() {
		return logger;
	}

}
