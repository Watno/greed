package greed.game;

import greed.game.cards.*;
import greed.game.eventtypes.*;
import greed.meta.JSONGenerator;
import greed.meta.Logger;
import server.games.IUserFromGamePerspective;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GreedGame {
    private int turnCounter = 0;
    private final ArrayList<GreedPlayer> players = new ArrayList<GreedPlayer>();
    private final ArrayList<GreedCard> drawPile = new ArrayList<GreedCard>();
    private final ArrayList<GreedCard> discardPile = new ArrayList<GreedCard>();
    private final ArrayList<AfterPlaysResolveEvent> afterPlaysResolveEvents = new ArrayList<AfterPlaysResolveEvent>();
    private final ArrayList<EachTurnEvent> eachTurnEvents = new ArrayList<EachTurnEvent>();
    private final ArrayList<EndOfTurnEvent> endOfTurnEvents = new ArrayList<EndOfTurnEvent>();
    private final ArrayList<EndOfGameEvent> endOfGameEvents = new ArrayList<EndOfGameEvent>();
    private ArrayList<TriggeredEvent> thisTurnEvents = new ArrayList<TriggeredEvent>();
    private ArrayList<TriggeredEvent> nextTurnEvents = new ArrayList<TriggeredEvent>();
    private final Logger logger = new Logger();
    private int realPlayers = 0;

    public GreedGame(int numberPlayers) {
        for (int i = 0; i < numberPlayers; i++) {
            GreedPlayer aPlayer = new GreedPlayer(this, "Player " + (i + 1), i);
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
