package carnivalOfMonsters.core.tests;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.meta.MockDecisionMaker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.junit.jupiter.api.RepeatedTest;

import java.util.List;

public class GameTest {

    @RepeatedTest(1000)
    void randomGameSmokeTest() throws JsonProcessingException {
        MockDecisionMaker firstDecisionMaker = new MockDecisionMaker("A");
        var game = new Game(List.of(new Player("A", firstDecisionMaker), new Player("B", new MockDecisionMaker("B")),
                new Player("C", new MockDecisionMaker("C")), new Player("D", new MockDecisionMaker("D"))));

        game.run();

        var serializer = new ObjectMapper();
        serializer.registerModule(new Jdk8Module());
        serializer.configure(MapperFeature.AUTO_DETECT_GETTERS, false);
        var json = serializer.writeValueAsString(firstDecisionMaker.gameState);
        System.out.println(json);


    }


}
