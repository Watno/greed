package carnivalOfMonsters.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import carnivalOfMonsters.core.monsters.Monster;

public class Player {
	public Player(IDecisionMaker decisionMaker) {
		super();
		this.decisionMaker = decisionMaker;
		decisionMaker.registerPlayer(this);
	}

	private IDecisionMaker decisionMaker;
	
	private List<ICanBeInPlay> cardsInPlay = new ArrayList<ICanBeInPlay>();
	private List<ICard> keptCards = new ArrayList<ICard>();
	private List<Monster> menagerie = new ArrayList<Monster>();
	
	private int crowns = 4;
	private int loans = 0;
	
	private int hunterTokens = 0;
	
	public IDecisionMaker getDecisionMaker() {
		return decisionMaker;
	}
	
	public void makeTurn(Collection<ICard> draftstack, Game game) {
		allowPlayingKeptCards(game);
		var cardToDraft = decisionMaker.pickCardToDraft(draftstack);
		if (!draftstack.contains(cardToDraft)) {
			throw new IllegalArgumentException();
		}
		
		if ((cardToDraft instanceof ICanBePlayed) && ((ICanBePlayed) cardToDraft).checkRequirement(this) && (decisionMaker.choosePlayOrKeep((ICanBePlayed) cardToDraft) == PlayOrKeep.PLAY)) {
			play((ICanBePlayed) cardToDraft, game);
		}
		else {
			keep(cardToDraft);
		}
		
		draftstack.remove(cardToDraft);
	}
	

	public void allowPlayingKeptCards(Game game) {
		while (!getPlayableKeptCards().isEmpty()) {
			var playableKeptCards = getPlayableKeptCards();
			var cardToPlay = decisionMaker.chooseKeptCardToPlay(playableKeptCards);
			if (cardToPlay.isPresent() && playableKeptCards.contains(cardToPlay.get())) {
				play(cardToPlay.get(), game);
			}
			else {
				break;
			}
		}
	}
	
	private void play(ICanBePlayed card, Game game) {
		if (card.checkRequirement(this)) {
			card.onPlay(this, game);
			if (card instanceof ICanBeInPlay) {
				cardsInPlay.add((ICanBeInPlay) card);
			}
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	private void keep(ICard cardToDraft) {
		pay(1);
		keptCards.add(cardToDraft);
		
	}
	
	private Collection<ICanBePlayed> getPlayableKeptCards(){
		return menagerie.stream().filter(x -> x instanceof ICanBePlayed).map(x -> (ICanBePlayed) x).collect(Collectors.toList());
	}
	
	public int getTotalLandPoints(LandType landType) {
		return cardsInPlay.stream()
				.mapToInt(x -> x.getProvidedLandPoints(landType))
				.sum();
	}
	
	public int getUsedLandPoints(LandType landType) {
		return cardsInPlay.stream()
				.mapToInt(x -> x.getConsumedLandPoints(landType))
				.sum();
	}
	
	public int getAvailableLandPoints(LandType landType) {
		return getTotalLandPoints(landType) - getUsedLandPoints(landType);
	}
	
	public int getDangerLevel() {
		return cardsInPlay.stream()
                .mapToInt(x -> x.getDangerLevel())
                .sum();
	}
	
	public Collection<Monster> getMenagerie() {
		return Collections.unmodifiableCollection(menagerie);
	}
	
	public Collection<ICard> getKeptCards() {
		return Collections.unmodifiableCollection(keptCards);
	}
	
	public int getLoans(){
		return loans;
	}
	
	public int getHunterTokens(){
		return hunterTokens;
	}
	
	public void draw(Game game, int numberOfCards) {
		keptCards.addAll((game.draw(numberOfCards)));
	}
	
	public void gainCrowns(int amount) {
		crowns += amount;
	}

	public void pay(int amount) {
		crowns -= amount;
		while (crowns < 0) {
			takeLoan();
		}
	}
	
	public void gainHunterToken() {
		hunterTokens++;
	}
	
	public void performDangerCheck(int royalHunters) {
		var missingHunters = getDangerLevel() - royalHunters;
		if (missingHunters > 0) {
			var compensatedByTokens = Integer.min(missingHunters, hunterTokens);
			hunterTokens -= compensatedByTokens;
			var stillMissingHunters = missingHunters - compensatedByTokens;
			if (stillMissingHunters > 0) {
				pay(stillMissingHunters);
			}
		}
	}
	
	public int score() {
		return Stream.of(
				menagerie.stream(),
				keptCards.stream().filter(x -> x instanceof ICanBeScored).map(x -> (ICanBeScored) x)
				)
		.flatMap(x -> x)
		.mapToInt(x -> x.score(this))
		.sum();
	}
	
	private void takeLoan() {
		loans++;
		crowns += 3;
	}

	

}
