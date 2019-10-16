package spacealert.core;

import java.util.Objects;
import java.util.Optional;

public class Position {
    private Deck deck;
    private Zone zone;

    public Position(Deck deck, Zone zone) {
        this.deck = deck;
        this.zone = zone;
    }

    public Optional<Position> getAdjacent(Direction direction){
        Deck newDeck = deck;
        Zone newZone = zone;
        switch (direction){

            case GRAVOLIFT:
                newDeck = deck.otherDeck();
            case BLUE:
                switch (zone){

                    case RED:
                        newZone = Zone.WHITE;
                        break;
                    case WHITE:
                        newZone = Zone.BLUE;
                        break;
                    case BLUE:
                        return Optional.empty();
                }
            case RED:
                switch (zone){

                    case RED:
                        return Optional.empty();
                    case WHITE:
                        newZone = Zone.RED;
                        break;
                    case BLUE:
                        newZone = Zone.WHITE;
                }
        }

        return Optional.of(new Position(newDeck, newZone));
    }

    public Deck getDeck() {
        return deck;
    }

    public Zone getZone() {
        return zone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return deck == position.deck &&
                zone == position.zone;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deck, zone);
    }
}
