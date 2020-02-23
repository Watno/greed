package carnivalOfMonsters.core;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import carnivalOfMonsters.core.monsters.Monster;

public class Player {
	private IDecisionMaker decisionMaker;
	
	private List<ICanBeInPlay> cardsInPlay;
	private List<ICard> keptCards = List.of();
	private List<Monster> menagerie = List.of();
	
	private int crowns = 4;
	private int loans = 0;
	
	private int hunterTokens = 0;
	
	public IDecisionMaker getDecisionMaker() {
		return decisionMaker;
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
	
	private void takeLoan() {
		loans++;
		crowns += 3;
	}
	

}
