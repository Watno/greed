package spacealert.tests;

import org.junit.jupiter.api.Test;
import server.connections.testing.MockConnectionSender;
import server.games.UserInGame;
import spacealert.core.planningPhase.PlanningPhase;
import spacealert.externalPlayerInterface.ExternalDecisionMaker;

import java.util.Arrays;

public class TestWithExternalPlayerInterface {
    @Test
    public void Test() {
        var userInGame = new UserInGame(new MockConnectionSender(), "Test");
        var planningPhase = new PlanningPhase(Arrays.asList(new ExternalDecisionMaker(userInGame)), 4);
        planningPhase.execute();
    }
}
