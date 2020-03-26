package carnivalOfMonsters.core.tests;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.meta.MockDecisionMaker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        var json = serializer.writeValueAsString(firstDecisionMaker.gameState);
        System.out.println(json);


    }


}
