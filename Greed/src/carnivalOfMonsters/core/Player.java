package carnivalOfMonsters.core;

import carnivalOfMonsters.core.gamestate.GameStateWithPrivateInfo;
import carnivalOfMonsters.core.gamestate.PrivateGameState;
import carnivalOfMonsters.core.gamestate.PublicGameState;
import carnivalOfMonsters.core.gamestate.PublicPlayerGameState;
import carnivalOfMonsters.core.monsters.Monster;
import carnivalOfMonsters.core.seasons.Season;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Player {
    private IDecisionMaker decisionMaker;
    private String name;

    private ArrayList<ICanBeInPlay> cardsInPlay = new ArrayList<>();
    private ArrayList<ICard> keptCards = new ArrayList<>();
    private ArrayList<Monster> menagerie = new ArrayList<>();
    private ArrayList<Season> trophies = new ArrayList<>();
    private int crowns = 4;
    private int loans = 0;
    private int hunterTokens = 0;

    public Player(String name, IDecisionMaker decisionMaker) {
        super();
        this.name = name;
        this.decisionMaker = decisionMaker;
    }

    public IDecisionMaker getDecisionMaker() {
        return decisionMaker;
    }

    public void makeTurn(Collection<ICard> draftstack, Game game) {
        var cardToDraft = decisionMaker.pickCardToDraft(draftstack);
        if (!draftstack.contains(cardToDraft)) {
            throw new IllegalArgumentException();
        }
        allowPlayingKeptCards(game);
        if ((cardToDraft instanceof ICanBePlayed) && ((ICanBePlayed) cardToDraft).checkRequirement(this) && (decisionMaker.choosePlayOrKeep((ICanBePlayed) cardToDraft) == PlayOrKeep.PLAY)) {
            play((ICanBePlayed) cardToDraft, game);
        } else {
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
                keptCards.remove(cardToPlay.get());
            } else {
                break;
            }
        }
    }

    private void play(ICanBePlayed card, Game game) {
        if (card.checkRequirement(this)) {
            card.onPlay(this, game);
            for (var effect : GetOnPlayEffects(game)) {
                if (effect.triggersOn(this, card)) {
                    effect.trigger(this, card);
                }

            }
            if (card instanceof ICanBeInPlay) {
                cardsInPlay.add((ICanBeInPlay) card);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void keep(ICard cardToDraft) {
        pay(1);
        keptCards.add(cardToDraft);

    }

    private Collection<ICanBePlayed> getPlayableKeptCards() {
        return keptCards.stream()
                .filter(x -> x instanceof ICanBePlayed)
                .map(x -> (ICanBePlayed) x)
                .filter(x -> x.checkRequirement(this))
                .collect(Collectors.toList());
    }

    public Map<LandType, Integer> getTotalLandPoints() {
        return Stream.of(LandType.values()).collect(Collectors.toMap(x -> x, this::getTotalLandPoints));
    }

    public int getTotalLandPoints(LandType landType) {
        return cardsInPlay.stream()
                .mapToInt(x -> x.getProvidedLandPoints(landType))
                .sum();
    }

    public Map<LandType, Integer> getUsedLandPoints() {
        return Stream.of(LandType.values()).collect(Collectors.toMap(x -> x, this::getUsedLandPoints));
    }

    public int getUsedLandPoints(LandType landType) {
        return cardsInPlay.stream()
                .mapToInt(x -> x.getConsumedLandPoints(landType))
                .sum();
    }

    public Map<LandType, Integer> getAvailableLandPoints() {
        return Stream.of(LandType.values()).collect(Collectors.toMap(x -> x, this::getAvailableLandPoints));
    }

    public int getAvailableLandPoints(LandType landType) {
        return getTotalLandPoints(landType) - getUsedLandPoints(landType);
    }

    public int getDangerLevel() {
        return cardsInPlay.stream()
                .mapToInt(ICanBeInPlay::getDangerLevel)
                .sum();
    }

    public Collection<Monster> getMenagerie() {
        return Collections.unmodifiableCollection(menagerie);
    }

    public Collection<ICard> getKeptCards() {
        return Collections.unmodifiableCollection(keptCards);
    }

    public Collection<ICanBeInPlay> getCardsInPlay() {
        return Collections.unmodifiableCollection(cardsInPlay);
    }

    public int getLoans() {
        return loans;
    }

    public int getHunterTokens() {
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

    public void assignTrophy(Season trophy) {
        trophies.add(trophy);
    }

    public void moveMonstersToMenagerie() {
        var monsters = cardsInPlay.stream().filter(x -> x instanceof Monster).map(x -> (Monster) x).collect(Collectors.toList());
        cardsInPlay.removeAll(monsters);
        menagerie.addAll(monsters);
    }

    public int score() {
        return Stream.of(
                menagerie.stream(),
                keptCards.stream().filter(x -> x instanceof ICanBeScored).map(x -> (ICanBeScored) x),
                trophies.stream()
        )
                .flatMap(x -> x)
                .mapToInt(x -> x.score(this))
                .sum()
                + crowns
                + hunterTokens
                - 3 * loans;
    }

    public void drawStartingLands(Game game) {
        for (var land : game.drawStartingLands()) {
            play(land, game);
        }
    }

    private void takeLoan() {
        loans++;
        crowns += 3;
    }

    private Collection<ITriggerOnPlay> GetOnPlayEffects(Game game) {
        List<ITriggerOnPlay> result = cardsInPlay.stream()
                .filter(x -> x instanceof ITriggerOnPlay)
                .map(x -> (ITriggerOnPlay) x)
                .collect(Collectors.toList());

        result.add(game.getCurrentSeason());

        return result;

    }

    public void sendGameStateToDecisionMaker(PublicGameState publicGameState) {
        decisionMaker.sendGameState(new GameStateWithPrivateInfo(publicGameState, getPrivateGameState()));


    }

    public PublicPlayerGameState getPublicPlayerGameState() {
        return new PublicPlayerGameState(name, cardsInPlay, menagerie, trophies, crowns, loans, hunterTokens, getTotalLandPoints(), getAvailableLandPoints(), getUsedLandPoints());
    }

    private PrivateGameState getPrivateGameState() {
        return new PrivateGameState(keptCards);
    }
}
