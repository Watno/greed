package spacealert.core;

import com.google.common.collect.Streams;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import spacealert.core.actionCards.ActionBoard;
import spacealert.core.actionCards.ActionCard;
import spacealert.core.actionCards.effects.*;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.missionSteps.DefaultMissionStepSequence;
import spacealert.core.planningPhase.eventSequences.threatProviders.RandomThreatProvider;
import spacealert.core.planningPhase.eventSequences.threatProviders.ThreatLevel;
import spacealert.core.threats.Trajectory;
import spacealert.core.threats.templates.Threat;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardStateTest {

    private static final Random random = new Random();

    @RepeatedTest(100000)
    void randomGameSmokeTest() {
        runRandomGame();
    }

    public BoardState runRandomGame() {
        var deck = ActionCard.defaultDeck();
        var actionBoards = IntStream.range(0, 5)
                .mapToObj(x -> createRandomActionBoard(deck)).collect(Collectors.toList());

        BoardState game = createBoardState(actionBoards);

        var missionStepSequence = new DefaultMissionStepSequence(getRandomThreatList());

        missionStepSequence.execute(game);
        return game;
    }


    @Test
    void pushA_ShouldKillTestThreat() {
        var actionBoard = createActionBoard(List.of(new AEffect()));

        var game = createBoardState(List.of(actionBoard));

        var missionStepSequence = new DefaultMissionStepSequence(Stream.generate(() -> List.<Threat>of(new TestThreat())).limit(12).collect(Collectors.toList()));

        missionStepSequence.execute(game);

        assertEquals(1, game.getDestroyedThreats().size());
    }

    @Test
    void pushAFourTimes_ShouldKill3TestThreat() {
        var actionBoard = createActionBoard(List.of(new AEffect(), new AEffect(), new AEffect(), new AEffect()));

        var game = createBoardState(List.of(actionBoard));

        var missionStepSequence = new DefaultMissionStepSequence(Stream.generate(() -> List.<Threat>of(new TestThreat())).limit(12).collect(Collectors.toList()));

        missionStepSequence.execute(game);

        assertEquals(3, game.getDestroyedThreats().size());
    }

    @Test
    void pushAFourTimes_Reload_PushAMore_ShouldKill5TestThreat() {
        var actionBoard = createActionBoard(List.of(new AEffect(), new AEffect(), new AEffect(), new AEffect(), new GravoliftMoveEffect(), new BEffect(), new GravoliftMoveEffect(), new AEffect(), new AEffect(), new AEffect(), new AEffect(), new AEffect()));

        var game = createBoardState(List.of(actionBoard));

        var missionStepSequence = new DefaultMissionStepSequence(Stream.generate(() -> List.<Threat>of(new TestThreat())).limit(12).collect(Collectors.toList()));

        missionStepSequence.execute(game);

        assertEquals(5, game.getDestroyedThreats().size());
    }

    @Test
    void moveRedThenC_thenBattlebot_ShouldEndInSpace() {
        var actionBoard = createActionBoard(List.of(
                new RedMoveEffect(), new GravoliftMoveEffect(), new CEffect(),
                new GravoliftMoveEffect(), new CEffect(), new AttackWithBattleBotEffect(),
                new AttackWithBattleBotEffect(), new AttackWithBattleBotEffect(),
                new AttackWithBattleBotEffect(), new AttackWithBattleBotEffect()));

        var game = createBoardState(List.of(actionBoard));

        var missionStepSequence = new DefaultMissionStepSequence(Stream.generate(() -> List.<Threat>of(new TestThreat())).limit(12).collect(Collectors.toList()));

        missionStepSequence.execute(game);

        var crewMember = game.getCrewMembers().iterator().next();

        assertEquals(game.getSpace(), crewMember.getLocation());
    }


    @Test
    void gravoliftMove_ShouldMoveToLowerWhite() {
        var actionBoard = createActionBoard(List.of(new GravoliftMoveEffect()));

        var game = createBoardState(List.of(actionBoard));

        var missionStepSequence = new DefaultMissionStepSequence(Collections.nCopies(12, new ArrayList<>()));

        missionStepSequence.execute(game);

        var crewMember = game.getCrewMembers().iterator().next();

        assertEquals(game.getStation(new Position(Deck.LOWER, Zone.WHITE)), crewMember.getLocation());
    }

    @Test
    void clockwiseRoundtrip_ShouldMoveToUpperWhite() {
        var actionBoard = createActionBoard(
                List.of(
                        new BlueMoveEffect(),
                        new GravoliftMoveEffect(),
                        new RedMoveEffect(),
                        new RedMoveEffect(),
                        new GravoliftMoveEffect(),
                        new BlueMoveEffect()));

        var game = createBoardState(List.of(actionBoard));

        var missionStepSequence = new DefaultMissionStepSequence(Collections.nCopies(12, new ArrayList<>()));

        missionStepSequence.execute(game);

        var crewMember = game.getCrewMembers().iterator().next();


        assertEquals(game.getStation(new Position(Deck.UPPER, Zone.WHITE)), crewMember.getLocation());
    }

    @Test
    void clockwiseRoundtrips_TwoInParalell_ShouldMoveToUpperWhite() {
        var actionBoard = createActionBoard(
                List.of(
                        new BlueMoveEffect(),
                        new GravoliftMoveEffect(),
                        new RedMoveEffect(),
                        new RedMoveEffect(),
                        new GravoliftMoveEffect(),
                        new BlueMoveEffect()));

        var game = createBoardState(List.of(actionBoard));

        var missionStepSequence = new DefaultMissionStepSequence(Collections.nCopies(12, new ArrayList<>()));

        missionStepSequence.execute(game);

        for (var crewMember : game.getCrewMembers()) {
            assertEquals(game.getStation(new Position(Deck.UPPER, Zone.WHITE)), crewMember.getLocation());
        }
    }

    @Test
    void counterClockwiseRoundtrip_ShouldMoveToUpperWhite() {
        var actionBoard = createActionBoard(
                List.of(
                        new RedMoveEffect(),
                        new GravoliftMoveEffect(),
                        new BlueMoveEffect(),
                        new BlueMoveEffect(),
                        new GravoliftMoveEffect(),
                        new RedMoveEffect()));

        var game = createBoardState(List.of(actionBoard));

        var missionStepSequence = new DefaultMissionStepSequence(Collections.nCopies(12, new ArrayList<>()));

        missionStepSequence.execute(game);

        var crewMember = game.getCrewMembers().iterator().next();


        assertEquals(game.getStation(new Position(Deck.UPPER, Zone.WHITE)), crewMember.getLocation());
    }


    private List<List<Threat>> getRandomThreatList() {
        var provider = new RandomThreatProvider(List.of(ThreatLevel.WHITE, ThreatLevel.YELLOW, ThreatLevel.RED), List.of(ThreatLevel.WHITE, ThreatLevel.YELLOW, ThreatLevel.RED));

        return Stream.generate(() -> getThreatFromProvider(provider)).limit(8)
                .map(List::of).collect(Collectors.toList());
    }

    private Threat getThreatFromProvider(RandomThreatProvider provider) {
        try {
            if (trueWithPercentage(25)) {
                return provider.provideExternalThreat(false).apply(getRandomZone());
            }
            if (trueWithPercentage(33)) {
                return provider.provideExternalThreat(true).apply(getRandomZone());
            }
            if (trueWithPercentage(50)) {
                return provider.provideInternalThreat(false).get();
            }
            return provider.provideInternalThreat(true).get();
        } catch (EmptyStackException e) {
            return getThreatFromProvider(provider);
        }
    }

    private Zone getRandomZone() {
        var index = random.nextInt(Zone.values().length);
        return Zone.values()[index];
    }

    private ActionBoard createActionBoard(List<ICardEffect> effects) {
        var actionBoard = new ActionBoard();
        var cards = effects.stream()
                .map(x -> new ActionCard(x, new AEffect()))
                .collect(Collectors.toList());
        int turn = 1;
        for (var card : cards) {
            actionBoard.tryPlace(turn, card, Optional.empty());
            turn++;
        }
        return actionBoard;
    }

    private ActionBoard createRandomActionBoard(List<ActionCard> deck) {
        var actionBoard = new ActionBoard();
        for (int turn = 1; turn <= 12; turn++) {
            if (trueWithPercentage(80)) {
                var card = deck.remove(0);
                if (trueWithPercentage(50)) {
                    card.flip();
                }
                actionBoard.tryPlace(turn, card, Optional.empty());
            }
        }
        return actionBoard;
    }

    private Collection<CrewMember> toCrewMembers(Collection<ActionBoard> actionBoards) {
        return Streams.zip(actionBoards.stream(),
                        Arrays.stream(Color.values()),
                        CrewMember::new)
                .collect(Collectors.toList());
    }

    private Boolean trueWithPercentage(int percentage) {
        return random.nextDouble() < (percentage / (double) 100);
    }


    private BoardState createBoardState(Collection<ActionBoard> actionBoards) {
        var allTrajectories = Trajectory.all();
        var trajectories = Map.of(
                Zone.RED, allTrajectories.remove(0),
                Zone.WHITE, allTrajectories.remove(0),
                Zone.BLUE, allTrajectories.remove(0)
        );

        var internalTrajectory = allTrajectories.remove(0);

        return new BoardState(toCrewMembers(actionBoards), trajectories, internalTrajectory);
    }
}