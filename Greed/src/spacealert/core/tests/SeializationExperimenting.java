package spacealert.core.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import spacealert.core.actionCards.ActionCard;

public class SeializationExperimenting {
    @Test
    void actionCard() throws JsonProcessingException {
        var sut = ActionCard.defaultDeck().get(0);
        var serializer = new ObjectMapper();
        var json = serializer.writeValueAsString(sut);
        System.out.println(json);
    }

}
