package carnivalOfMonsters.core;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardGeneratorTest {
    @Test
    void createDrawPile_shouldHaveCount_206() {
        var drawPile = CardGenerator.createDrawPile();

        assertEquals(206, drawPile.size());
    }

    @Test
    void createDrawPile_shouldNotHaveDuplicateNames() {
        var drawPile = CardGenerator.createDrawPile();

        var cardsWithSameName = drawPile.stream().collect(Collectors.groupingBy(ICard::getName));

        for (var name : cardsWithSameName.keySet()) {
            var referenceCard = cardsWithSameName.get(name).get(0);
            for (var card : cardsWithSameName.get(name)) {
                assertEquals(referenceCard.getClass(), card.getClass());
                if (referenceCard instanceof ICanBeInPlay) {
                    for (var landType : LandType.values()) {
                        assertEquals(((ICanBeInPlay) referenceCard).getConsumedLandPoints(landType), ((ICanBeInPlay) card).getConsumedLandPoints(landType),
                                "" + card.getClass() + referenceCard.getClass() + landType);
                        assertEquals(((ICanBeInPlay) referenceCard).getProvidedLandPoints(landType), ((ICanBeInPlay) card).getProvidedLandPoints(landType),
                                "" + card.getClass() + referenceCard.getClass() + landType);
                    }
                }
            }
        }
    }
}
