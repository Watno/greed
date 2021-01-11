package spacealert.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.junit.jupiter.api.Test;
import spacealert.core.actionCards.ActionBoard;
import spacealert.core.actionCards.ActionCard;

public class SerializationExperimenting {
    @Test
    void actionCard() throws JsonProcessingException {
        var sut = ActionCard.defaultDeck().get(0);
        var serializer = new ObjectMapper();
        var json = serializer.writeValueAsString(sut);
        System.out.println(json);
    }

    @Test
    void actionBoard() throws JsonProcessingException {
        var sut = new ActionBoard();
        sut.place(1, ActionCard.defaultDeck().get(0));
        var serializer = new ObjectMapper();
        serializer.registerModule(new Jdk8Module());
        var json = serializer.writeValueAsString(sut);
        System.out.println(json);
    }
}
