package carnivalOfMonsters.core;

import carnivalOfMonsters.core.gamestate.PublicGameState;
import carnivalOfMonsters.core.lands.BasicNormalLand;
import carnivalOfMonsters.core.seasons.Season;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Game implements Runnable {
    private Stack<ICard> drawPile;

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

        sendGameStateToPlayers();
        for (var player : players) {
            System.out.println(player.score());
        }

    }

    private void runSeason(int season) {
        sendGameStateToPlayers();
        var draftStacks = createDraftstacks();
        for (int round = 1; round <= 8; round++) {
            int localRound = round;
            IntStream.range(0, players.size()).parallel().forEach(playernumber ->
                    players.get(playernumber)
                            .makeTurn(draftStacks.get(Math.floorMod((int) (Math.pow(-1, season)) * playernumber + localRound, players.size())), this));
            sendGameStateToPlayers();
        }

        for (var player : players) {
            player.allowPlayingKeptCards(this);
        }

        sendGameStateToPlayers();

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

    private void sendGameStateToPlayers() {
        for (var player : players) {
            player.sendGameStateToDecisionMaker(getPublicGameState());
        }
    }

    private PublicGameState getPublicGameState() {
        return new PublicGameState(getCurrentSeason(), players.stream().map(x -> x.getPublicPlayerGameState()).collect(Collectors.toList()));
    }
}
