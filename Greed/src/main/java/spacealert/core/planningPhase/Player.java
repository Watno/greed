package spacealert.core.planningPhase;

import spacealert.core.Color;
import spacealert.core.actionCards.ActionBoard;
import spacealert.core.actionCards.ActionCard;
import spacealert.core.gamestates.PrivateGameState;
import spacealert.core.gamestates.PublicPlayerGameState;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public class Player {
    private Color color;
    private ArrayList<ActionCard> hand;
    private ActionBoard actionBoard;

    public Player(Color color) {
        this.color = color;
        hand = new ArrayList<>();
        actionBoard = new ActionBoard();
    }

    public void flipCardInHand(UUID cardId) {
        findCardInHand(cardId).ifPresent(ActionCard::flip);
    }

    public void flipCardOnActionBoard(UUID cardId) {
        actionBoard.flipCardById(cardId);
    }

    public void placeCardOnOwnActionBoard(UUID cardId, int position) {
        placeCardOnActionBoard(actionBoard, cardId, position);
    }

    public void placeCardOnActionBoard(ActionBoard actionBoard, UUID cardId, int position) {
        removeCardFromHand(cardId)
                .ifPresent(card -> actionBoard.place(position, card));
    }

    public void placeCardOnAndroidActionBoard(Android android, UUID cardId, int position) {
        removeCardFromHand(cardId)
                .ifPresent(card -> android.placeCardOnActionBoard(card, position));
    }

    public void retrieveCardFromAndroidActionBoard(Android android, UUID cardId) {
        android.retrieveCardFromActionBoard(cardId)
                .ifPresent(x -> hand.add(x));
    }

    public void retrieveCardFromActionBoard(UUID cardId) {
        actionBoard.returnCardById(cardId)
                .ifPresent(x -> hand.add(x));
    }

    private Optional<ActionCard> removeCardFromHand(UUID cardId) {
        var card = findCardInHand(cardId);
        card.ifPresent(x -> hand.remove(x));
        return card;
    }

    private Optional<ActionCard> findCardInHand(UUID cardId) {
        return hand.stream().filter(x -> x.id.equals(cardId)).findFirst();
    }

    public PublicPlayerGameState toPublicPlayerGameState() {
        return new PublicPlayerGameState(color, actionBoard, hand.size());
    }

    public PrivateGameState toPrivateGameState() {
        return new PrivateGameState(color, hand);
    }

}
