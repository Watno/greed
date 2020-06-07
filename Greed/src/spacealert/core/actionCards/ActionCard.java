package spacealert.core.actionCards;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.Game;
import spacealert.core.ICrewMember;
import spacealert.core.actionCards.effects.*;
import spacealert.core.boardElements.positions.Direction;

import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ActionCard {
    @JsonProperty
    private ICardEffect actionHalf;
    @JsonProperty
    private ICardEffect movementHalf;
    @JsonProperty
    private CardOrientation orientation;

    public ActionCard(ICardEffect actionHalf, ICardEffect movementHalf) {
        this(actionHalf, movementHalf, CardOrientation.ACTION);
    }

    private ActionCard(ICardEffect actionHalf, ICardEffect movementHalf, CardOrientation orientation) {
        super();
        this.actionHalf = actionHalf;
        this.movementHalf = movementHalf;
        this.orientation = orientation;
    }

    public static List<ActionCard> defaultDeck() {
        var deck = Stream.of(Direction.GRAVOLIFT, Direction.BLUE, Direction.RED)
                .map(ActionCard::getMoveFromDirection)
                .flatMap(x -> Stream.of(
                        IntStream.range(0, 10)
                                .mapToObj(n -> new ActionCard(new AEffect(), x.get())),
                        IntStream.range(0, 8)
                                .mapToObj(n -> new ActionCard(new BEffect(), x.get())),
                        IntStream.range(0, 7)
                                .mapToObj(n -> new ActionCard(new CEffect(), x.get())),
                        IntStream.range(0, 5)
                                .mapToObj(n -> new ActionCard(new AttackWithBattleBotEffect(), x.get()))
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
