package spacealert.tests;

import org.junit.jupiter.api.Test;
import server.connections.serialization.ObjectMapperProvider;
import server.connections.testing.MockConnectionSender;
import server.games.UserInGame;
import spacealert.core.planningPhase.PlanningPhase;
import spacealert.core.planningPhase.commands.actionCards.FlipCardInHandCommand;
import spacealert.externalPlayerInterface.ExternalDecisionMaker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.UUID;

public class TestWithExternalPlayerInterface {
    @Test
    public void test() {
        var userInGame = new UserInGame(new MockConnectionSender(), "Test");
        new Thread(() -> doInputs(userInGame)).start();
        var planningPhase = new PlanningPhase(Arrays.asList(new ExternalDecisionMaker(userInGame)), 4);
        planningPhase.execute();
    }

    public void inputFromConsole(UserInGame userInGame){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String command = null;
            try {
                command = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            userInGame.receiveInput(command);
        }
    }

    public void doInputs(UserInGame userInGame){
        var serializer = new ObjectMapperProvider().provide();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userInGame.receiveInput(serializer.valueToTree(new FlipCardInHandCommand(UUID.randomUUID())).toString());
    }
}
