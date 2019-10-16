package spacealert.core.actionCards;

import spacealert.core.Direction;
import spacealert.core.Game;
import spacealert.core.ICrewMember;
import spacealert.core.actionCards.effects.*;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Card {
    private ICardEffect actionHalf;
    private ICardEffect movementHalf;
    private CardOrientation orientation;

    private Card(ICardEffect actionHalf, ICardEffect movementHalf) {
        this(actionHalf, movementHalf, CardOrientation.ACTION);
    }

    private Card(ICardEffect actionHalf, ICardEffect movementHalf, CardOrientation orientation) {
        super();
        this.actionHalf = actionHalf;
        this.movementHalf = movementHalf;
        this.orientation = orientation;
    }

    public static List<Card> defaultDeck() {
        var deck = Stream.of(Direction.GRAVOLIFT, Direction.BLUE, Direction.RED)
                .map(Card::getMoveFromDirection)
                .flatMap(x -> Stream.of(
                        IntStream.range(0, 10)
                                .mapToObj(n -> new Card(new AEffect(), x.get())),
                        IntStream.range(0, 8)
                                .mapToObj(n -> new Card(new BEffect(), x.get())),
                        IntStream.range(0, 7)
                                .mapToObj(n -> new Card(new CEffect(), x.get())),
                        IntStream.range(0, 5)
                                .mapToObj(n -> new Card(new AttackWithBattleBotEffect(), x.get()))
                ).flatMap(y -> y)).collect(Collectors.toList());
        Collections.shuffle(deck);
        return deck;
    }

    private static Supplier<ICardEffect> getMoveFromDirection(Direction direction) {
        switch (direction) {
            case GRAVOLIFT:
                return GravoliftMoveEffect::new;
            case BLUE:
                return BlueMoveEffect::new;
            case RED:
                return RedMoveEffect::new;
            default:
                throw new IllegalStateException("Unexpected value: " + direction);
        }
    }

    public void execute(ICrewMember crewMember, Game game) {
        var activeHalf = getActiveHalf();
        activeHalf.execute(crewMember, game);
    }

    public void flip() {
        orientation = orientation.flip();
    }

    private ICardEffect getActiveHalf() {
        switch (orientation) {
            case ACTION:
                return actionHalf;
            case MOVEMENT:
                return movementHalf;
            default:
                throw new IllegalArgumentException();
        }
    }
}
