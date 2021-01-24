package spacealert.core.planningPhase;

import spacealert.core.Color;
import spacealert.core.Phase;
import spacealert.core.actionCards.ActionBoard;
import spacealert.core.actionCards.ActionCard;
import spacealert.core.gamestates.PrivateGameState;
import spacealert.core.gamestates.PublicPlayerGameState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class Player {
    private final Color color;
    private final ArrayList<ActionCard> hand;
    private final ActionBoard actionBoard;
    private boolean isAllowedToPassACard = false;
    private Phase phase = Phase.ONE;

    public Player(Color color) {
        this.color = color;
        hand = new ArrayList<>();
        actionBoard = new ActionBoard();
    }

    public void receiveCard(ActionCard card) {
        hand.add(card);
    }

    public void receiveCards(Collection<ActionCard> cards) {
        for (var card : cards) {
            receiveCard(card);
        }
    }

    public void flipCardInHand(UUID cardId) {
        findCardInHand(cardId).ifPresent(ActionCard::flip);
    }

    public void flipCardOnActionBoard(UUID cardId) {
        actionBoard.flipCardById(cardId, phase);
    }

    public void flipCardOnAndroidActionBoard(Android android, UUID cardId) {
        android.flipCardOnActionBoard(cardId, phase);
    }

    public void placeCardOnOwnActionBoard(UUID cardId, int position) {
        placeCardOnActionBoard(actionBoard, cardId, position);
    }

    public void placeCardOnActionBoard(ActionBoard actionBoard, UUID cardId, int position) {
        findCardInHand(cardId)
                .ifPresent(card -> {
                    if (actionBoard.tryPlace(position, card, Optional.of(phase))) {
                        removeCardFromHand(cardId);
                    }
                });
    }

    public void placeCardOnAndroidActionBoard(Android android, UUID cardId, int position) {
        findCardInHand(cardId)
                .ifPresent(card -> {
                    if (android.tryPlaceCardOnActionBoard(card, position, Optional.of(phase))) {
                        removeCardFromHand(cardId);
                    }
                });
    }

    public void retrieveCardFromAndroidActionBoard(Android android, UUID cardId) {
        android.retrieveCardFromActionBoard(cardId, phase)
                .ifPresent(hand::add);
    }

    public void retrieveCardFromActionBoard(UUID cardId) {
        actionBoard.returnCardById(cardId, phase)
                .ifPresent(hand::add);
    }

    public void allowToPassACard() {
        isAllowedToPassACard = true;
    }

    public void disallowToPassACard() {
        isAllowedToPassACard = false;
    }

    public void passCardTo(Player receivingPlayer, UUID cardId) {
        if (!isAllowedToPassACard) return;
        removeCardFromHand(cardId).ifPresent(x -> {
            receivingPlayer.receiveCard(x);
            disallowToPassACard();
        });
    }

    public boolean endPhase(Phase phase) {
        if (this.phase == phase && phase != Phase.THREE) {
            this.phase = phase.next();
            return true;
        } else {
            return false;
        }
    }

    private Optional<ActionCard> removeCardFromHand(UUID cardId) {
        var card = findCardInHand(cardId);
        card.ifPresent(hand::remove);
        return card;
    }

    private Optional<ActionCard> findCardInHand(UUID cardId) {
        return hand.stream().filter(x -> x.id.equals(cardId)).findFirst();
    }

    public PublicPlayerGameState toPublicPlayerGameState() {
        return new PublicPlayerGameState(color, actionBoard, hand.size(), isAllowedToPassACard);
    }

    public PrivateGameState toPrivateGameState() {
        return new PrivateGameState(color, hand);
    }

    public boolean hasColor(Color color) {
        return this.color == color;
    }

}
