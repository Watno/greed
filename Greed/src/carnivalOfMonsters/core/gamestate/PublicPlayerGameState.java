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
    public Collection<Season> trophies;
    public int crowns;
    public int loans;
    public int hunterTokens;
    public Map<LandType, Integer> getTotalLandPoints;
    public Map<LandType, Integer> getAvailableLandPoints;
    public Map<LandType, Integer> getUsedLandPoints;

    public PublicPlayerGameState(String name, Collection<ICanBeInPlay> cardsInPlay, Collection<Monster> menagerie, Collection<Season> trophies, int crowns, int loans, int hunterTokens, Map<LandType, Integer> getTotalLandPoints, Map<LandType, Integer> getAvailableLandPoints, Map<LandType, Integer> getUsedLandPoints) {
        this.name = name;
        this.cardsInPlay = cardsInPlay;
        this.menagerie = menagerie;
        this.trophies = trophies;
        this.crowns = crowns;
        this.loans = loans;
        this.hunterTokens = hunterTokens;
        this.getTotalLandPoints = getTotalLandPoints;
        this.getAvailableLandPoints = getAvailableLandPoints;
        this.getUsedLandPoints = getUsedLandPoints;
    }

}
