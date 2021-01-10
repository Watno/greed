package carnivalOfMonsters.core;

import carnivalOfMonsters.core.gamestate.PublicGameState;
import carnivalOfMonsters.core.lands.BasicNormalLand;
import carnivalOfMonsters.core.logging.*;
import carnivalOfMonsters.core.seasons.Season;
import carnivalOfMonsters.core.secretGoals.SecondRowIsGoodEnough;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Game implements Runnable {
    private final Stack<ICard> drawPile;

    private final List<Player> players;

    private final Collection<HuntDie> huntDice;

    private final Stack<Season> seasons;
    private final Stack<BasicNormalLand> startingLands;

    private final GameLogEntry gamelog = new GameLogEntry();

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

        ScorePhaseLogEntry scorePhaseLogEntry = new ScorePhaseLogEntry();
        gamelog.addDependantEntry(scorePhaseLogEntry);
        var scores = players.stream().collect(Collectors.toMap(x -> x, x -> x.score(Optional.of(scorePhaseLogEntry))));
        var rankedPlayers = players.stream().sorted(Comparator.comparing(x -> scores.get(x)).reversed()).collect(Collectors.toList());
        for (var player : players) {
            var score = scores.get(player);
            if (player.getKeptCards().stream().anyMatch(x -> x instanceof SecondRowIsGoodEnough)) {
                if (score == scores.get(rankedPlayers.get(1))) {
                    score += 7;
                    scorePhaseLogEntry.addDependantEntry(new SecondRowLogEntry(player.getName(), player.getKeptCards().stream().filter(x -> x instanceof SecondRowIsGoodEnough).map(x -> (SecondRowIsGoodEnough) x).findFirst().get()));
                }
            }
            System.out.println(player.getName() + ": " + score);
        }
        sendGameStateToPlayers();
    }

    private void runSeason(int season) {
        var seasonLogEntry = new SeasonLogEntry(season);
        gamelog.addDependantEntry(seasonLogEntry);

        sendGameStateToPlayers();
        var draftStacks = createDraftstacks();
        for (int round = 1; round <= 8; round++) {
            int localRound = round;
            var turnLogEntry = new TurnLogEntry(localRound);
            seasonLogEntry.addDependantEntry(turnLogEntry);
            var playerTurnLogs = new ConcurrentHashMap<Player, ILogEntry>();
            IntStream.range(0, players.size()).parallel().forEach(playernumber ->
            {
                var player = players.get(playernumber);
                var log = player.makeTurn(draftStacks.get(Math.floorMod((int) (Math.pow(-1, season)) * playernumber + localRound, players.size())), this);
                playerTurnLogs.put(player, log);
            });
            for (var player : players) {
                turnLogEntry.addDependantEntry(playerTurnLogs.get(player));
            }

            sendGameStateToPlayers();
        }

        for (var player : players) {
            player.allowPlayingKeptCards(this, Optional.of(seasonLogEntry));
        }

        sendGameStateToPlayers();

        var rolls = huntDice.stream().map(HuntDie::roll).collect(Collectors.toList());
        HuntPhaseEntry huntPhaseEntry = new HuntPhaseEntry(rolls);
        seasonLogEntry.addDependantEntry(huntPhaseEntry);
        var royalHunters = rolls.stream().mapToInt(x -> x).sum();
        for (var player : players) {
            player.performDangerCheck(royalHunters, Optional.of(huntPhaseEntry));
        }

        getCurrentSeason().assign(players, Optional.of(seasonLogEntry));
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
        return new PublicGameState(getCurrentSeason(), players.stream().map(x -> x.getPublicPlayerGameState()).collect(Collectors.toList()), gamelog);
    }
}
