package carnivalOfMonsters.core.gamestate;

import carnivalOfMonsters.core.ICanBeInPlay;
import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.monsters.Monster;
import carnivalOfMonsters.core.seasons.Season;

import java.util.Collection;
import java.util.Map;

public class PublicPlayerGameState {
    final public String name;
    final public Collection<ICanBeInPlay> cardsInPlay;
    final public Collection<Monster> menagerie;
    final public int numberOfKeptCards;
    final public Collection<Season> trophies;
    final public int crowns;
    final public int loans;
    final public int hunterTokens;
    final public Map<LandType, Integer> totalLandPoints;
    final public Map<LandType, Integer> availableLandPoints;
    final public Map<LandType, Integer> usedLandPoints;

    public PublicPlayerGameState(String name, Collection<ICanBeInPlay> cardsInPlay, Collection<Monster> menagerie, int numberOfKeptCards, Collection<Season> trophies, int crowns, int loans, int hunterTokens, Map<LandType, Integer> getTotalLandPoints, Map<LandType, Integer> getAvailableLandPoints, Map<LandType, Integer> getUsedLandPoints) {
        this.name = name;
        this.cardsInPlay = cardsInPlay;
        this.menagerie = menagerie;
        this.numberOfKeptCards = numberOfKeptCards;
        this.trophies = trophies;
        this.crowns = crowns;
        this.loans = loans;
        this.hunterTokens = hunterTokens;
        this.totalLandPoints = getTotalLandPoints;
        this.availableLandPoints = getAvailableLandPoints;
        this.usedLandPoints = getUsedLandPoints;
    }

}
