package spacealert.core.tests;

import org.junit.jupiter.api.Test;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Direction;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {
    @Test
    void getAdjacent_UpperWhite_DirectionBlue_ShouldReturnUpperBlue() {
        Test(Deck.UPPER, Zone.WHITE, Direction.BLUE, Deck.UPPER, Zone.BLUE);
    }

    @Test
    void getAdjacent_UpperWhite_DirectionRed_ShouldReturnUpperRed() {
        Test(Deck.UPPER, Zone.WHITE, Direction.RED, Deck.UPPER, Zone.RED);
    }

    @Test
    void getAdjacent_UpperWhite_DirectionGravolift_ShouldReturnLowerWhite() {
        Test(Deck.UPPER, Zone.WHITE, Direction.GRAVOLIFT, Deck.LOWER, Zone.WHITE);
    }

    @Test
    void getAdjacent_LowerRed_DirectionBlue_ShouldReturnLowerWhite() {
        Test(Deck.LOWER, Zone.RED, Direction.BLUE, Deck.LOWER, Zone.WHITE);
    }

    @Test
    void getAdjacent_LowerRed_DirectionRed_ShouldReturnEmpty() {
        TestNoChange(Deck.LOWER, Zone.RED, Direction.RED);
    }

    @Test
    void getAdjacent_LowerRed_DirectionGravolift_ShouldReturnUpperRed() {
        Test(Deck.LOWER, Zone.RED, Direction.GRAVOLIFT, Deck.UPPER, Zone.RED);
    }

    @Test
    void getAdjacent_UpperBlue_DirectionBlue_ShouldReturnEmpty() {
        TestNoChange(Deck.UPPER, Zone.BLUE, Direction.BLUE);
    }

    @Test
    void getAdjacent_UpperBlue_DirectionRed_ShouldReturnUpperWhite() {
        Test(Deck.UPPER, Zone.BLUE, Direction.RED, Deck.UPPER, Zone.WHITE);
    }

    @Test
    void getAdjacent_UpperBlue_DirectionGravolift_ShouldReturnLowerBlue() {
        Test(Deck.UPPER, Zone.BLUE, Direction.GRAVOLIFT, Deck.LOWER, Zone.BLUE);
    }


    private void Test(Deck originalDeck, Zone originalZone, Direction direction, Deck newDeck, Zone newZone) {
        Optional<Position> result = Execute(originalDeck, originalZone, direction);

        assertEquals(result, Optional.of(new Position(newDeck, newZone)));
    }

    private void TestNoChange(Deck originalDeck, Zone originalZone, Direction direction) {
        Optional<Position> result = Execute(originalDeck, originalZone, direction);

        assertEquals(result, Optional.empty());
    }

    private Optional<Position> Execute(Deck originalDeck, Zone originalZone, Direction direction) {
        var originalPosition = new Position(originalDeck, originalZone);
        return originalPosition.getAdjacent(direction);
    }
}