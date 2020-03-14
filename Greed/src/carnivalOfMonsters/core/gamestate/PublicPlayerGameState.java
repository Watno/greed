package carnivalOfMonsters.core.gamestate;

import carnivalOfMonsters.core.ICanBeInPlay;
import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.monsters.Monster;
import carnivalOfMonsters.core.seasons.Season;

import java.util.Collection;
import java.util.Map;

public class PublicPlayerGameState {
    public String name;
    public Collection<ICanBeInPlay> cardsInPlay;
    public Collection<Monster> menagerie;
    public int numberOfKeptCards;
    public Collection<Season> trophies;
    public int crowns;
    public int loans;
    public int hunterTokens;
    public Map<LandType, Integer> totalLandPoints;
    public Map<LandType, Integer> availableLandPoints;
    public Map<LandType, Integer> usedLandPoints;

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
