package spacealert.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import server.connections.serialization.ObjectMapperProvider;
import spacealert.core.actionCards.ActionBoard;
import spacealert.core.actionCards.ActionCard;

import java.util.Optional;

public class SerializationExperimenting {
    @Test
    void actionCard() throws JsonProcessingException {
        var sut = ActionCard.defaultDeck().get(0);
        var serializer = ObjectMapperProvider.provide();
        var json = serializer.writeValueAsString(sut);
        System.out.println(json);
    }

    @Test
    void actionBoard() throws JsonProcessingException {
        var sut = new ActionBoard();
        sut.tryPlace(1, ActionCard.defaultDeck().get(0), Optional.empty());
        var serializer = ObjectMapperProvider.provide();
        var json = serializer.writeValueAsString(sut);
        System.out.println(json);
    }

    @Test
    void serializeGamestate() throws JsonProcessingException {
        var sut = new BoardStateTest().runRandomGame();
        var serializer = ObjectMapperProvider.provide();
        var json = serializer.writerWithDefaultPrettyPrinter().writeValueAsString(sut);
        System.out.print(json);
    }
}
