package carnivalOfMonsters.core.tests;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.MockDecisionMaker;
import carnivalOfMonsters.core.Player;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.RepeatedTest;

import java.util.List;

public class GameTest {

    @RepeatedTest(1000)
    void randomGameSmokeTest() throws JsonProcessingException {
        var game = new Game(List.of(new Player(new MockDecisionMaker()), new Player(new MockDecisionMaker()),
                new Player(new MockDecisionMaker()), new Player(new MockDecisionMaker())));

        game.run();

        var serializer = new ObjectMapper();
        serializer.disable(MapperFeature.AUTO_DETECT_CREATORS,
                MapperFeature.AUTO_DETECT_FIELDS,
                MapperFeature.AUTO_DETECT_GETTERS,
                MapperFeature.AUTO_DETECT_IS_GETTERS);
        var json = serializer.writeValueAsString(game);
        System.out.println(json);
    }


}
