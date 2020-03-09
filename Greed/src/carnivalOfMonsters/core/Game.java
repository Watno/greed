package carnivalOfMonsters.core;

import carnivalOfMonsters.core.lands.BasicNormalLand;
import carnivalOfMonsters.core.seasons.Season;
import com.google.gson.annotations.Expose;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {
    private Stack<ICard> drawPile;

    @Expose
    private List<Player> players;

    private Collection<HuntDie> huntDice;

    private Stack<Season> seasons;
    private Stack<BasicNormalLand> startingLands;

    public Game(Collection<Player> players) {
        super();
        this.players = new ArrayList<>(players);
        drawPile = CardGenerator.createDrawPile();
        createDraftstacks();

        seasons = CardGenerator.createSeasons();

        startingLands = CardGenerator.createStartingLands();

        var random = new Random();
        huntDice = Stream.generate(() -> new HuntDie(random)).limit(3).collect(Collectors.toList());
    }

    public void run() {

        for (var player : players) {
            player.drawStartingLands(this);
        }

        for (int season = 1; season <= 4; season++) {
            runSeason(season);
        }
        for (var player : players) {
            System.out.println(player.score());
        }

    }

    private void runSeason(int season) {
        var draftStacks = createDraftstacks();
        for (int round = 1; round <= 8; round++) {
            for (int player = 0; player < players.size(); player++) {
                // TODO parallel playing
                players.get(player).makeTurn(
                        draftStacks.get(Math.floorMod((int) (Math.pow(-1, season)) * player + round, players.size())),
                        this);
            }
        }
        for (var player : players) {
            player.allowPlayingKeptCards(this);
        }

        var royalHunters = huntDice.stream().mapToInt(HuntDie::roll).sum();
        for (var player : players) {
            player.performDangerCheck(royalHunters);
        }

        getCurrentSeason().assign(players);
        seasons.pop();

        for (var player : players) {
            player.moveMonstersToMenagerie();
        }


    }

    public Collection<ICard> draw(int numberOfCards) {
        return Stream.generate(() -> drawPile.pop()).limit(Integer.min(numberOfCards, drawPile.size()))
                .collect(Collectors.toList());
    }

    public Collection<BasicNormalLand> drawStartingLands() {
        return Stream.generate(() -> startingLands.pop()).limit(2)
                .collect(Collectors.toList());
    }

    public Season getCurrentSeason() {
        return seasons.peek();
    }

    private List<Collection<ICard>> createDraftstacks() {
        return players.stream().map(x -> draw(8)).collect(Collectors.toList());
    }

}
