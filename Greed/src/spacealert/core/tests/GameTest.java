package spacealert.core.tests;

import org.junit.jupiter.api.RepeatedTest;
import spacealert.core.Game;
import spacealert.core.actionCards.ActionBoard;
import spacealert.core.actionCards.Card;
import spacealert.core.missionSteps.DefaultMissionStepSequence;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class GameTest {

    private static Random random = new Random();

    @RepeatedTest(100)
    public void randomGameSmokeTest() {
        var deck = Card.defaultDeck();
        var actionBoards = IntStream.range(0, 5)
                .mapToObj(x -> CreateRandomActionBoard(deck)).collect(Collectors.toList());

        var game = new Game(actionBoards);

        var missionStepSequence = new DefaultMissionStepSequence();

        missionStepSequence.execute(game);
    }

    private ActionBoard CreateRandomActionBoard(List<Card> deck) {
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