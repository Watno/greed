package carnivalOfMonsters.core.tests;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.MockDecisionMaker;
import carnivalOfMonsters.core.Player;
import org.junit.jupiter.api.RepeatedTest;

import java.util.List;

public class GameTest {

    @RepeatedTest(1000)
    void randomGameSmokeTest() {
        var game = new Game(List.of(new Player(new MockDecisionMaker()), new Player(new MockDecisionMaker()),
                new Player(new MockDecisionMaker()), new Player(new MockDecisionMaker())));

        game.run();
    }


}
