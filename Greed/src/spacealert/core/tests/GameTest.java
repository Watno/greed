package spacealert.core.tests;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import spacealert.core.Game;
import spacealert.core.actionCards.ActionBoard;
import spacealert.core.actionCards.Card;
import spacealert.core.actionCards.effects.*;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.missionSteps.DefaultMissionStepSequence;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameTest {

    private static Random random = new Random();

    @RepeatedTest(100)
    void randomGameSmokeTest() {
        var deck = Card.defaultDeck();
        var actionBoards = IntStream.range(0, 5)
                .mapToObj(x -> createRandomActionBoard(deck)).collect(Collectors.toList());

        var game = new Game(actionBoards);

        var missionStepSequence = new DefaultMissionStepSequence();

        missionStepSequence.execute(game);
    }

    @Test
    void gravoliftMove_ShouldMoveToLowerWhite() {
        var actionBoard = createActionBoard(List.of(new GravoliftMoveEffect()));

        var game = new Game(List.of(actionBoard));

        var missionStepSequence = new DefaultMissionStepSequence();

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

        var game = new Game(List.of(actionBoard));

        var missionStepSequence = new DefaultMissionStepSequence();

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
        var actionBoard2 = createActionBoard(
                List.of(
                        new BlueMoveEffect(),
                        new GravoliftMoveEffect(),
                        new RedMoveEffect(),
                        new RedMoveEffect(),
                        new GravoliftMoveEffect(),
                        new BlueMoveEffect()));

        var game = new Game(List.of(actionBoard, actionBoard2));

        var missionStepSequence = new DefaultMissionStepSequence();

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

        var game = new Game(List.of(actionBoard));

        var missionStepSequence = new DefaultMissionStepSequence();

        missionStepSequence.execute(game);

        var crewMember = game.getCrewMembers().iterator().next();


        assertEquals(game.getStation(new Position(Deck.UPPER, Zone.WHITE)), crewMember.getLocation());
    }


    private ActionBoard createActionBoard(List<ICardEffect> effects) {
        var actionBoard = new ActionBoard();
        var cards = effects.stream()
                .map(x -> new Card(x, new AEffect()))
                .collect(Collectors.toList());
        int turn = 1;
        for (var card : cards) {
            actionBoard.place(turn, card);
            turn++;
        }
        return actionBoard;
    }

    private ActionBoard createRandomActionBoard(List<Card> deck) {
        var actionBoard = new ActionBoard();
        for (int turn = 1; turn <= 12; turn++) {
            if (trueWithPercentage(80)) {
                var card = deck.remove(0);
                if (trueWithPercentage(50)) {
                    card.flip();
                }
                actionBoard.place(turn, card);
            }
        }
        return actionBoard;
    }

    private Boolean trueWithPercentage(int percentage) {
        return random.nextDouble() < (percentage / (double) 100);
    }
}