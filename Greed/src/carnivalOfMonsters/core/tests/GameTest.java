package carnivalOfMonsters.core.tests;

import java.util.List;

import org.junit.jupiter.api.RepeatedTest;
import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.MockDecisionMaker;
import carnivalOfMonsters.core.Player;

public class GameTest {

	@RepeatedTest(1000)
	void randomGameSmokeTest() {
		var game = new Game(List.of(new Player(new MockDecisionMaker()), new Player(new MockDecisionMaker()),
				new Player(new MockDecisionMaker()), new Player(new MockDecisionMaker())));

		game.run();
	}
	

}
