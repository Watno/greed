package spacealert;

import org.junit.jupiter.api.Test;
import server.MockConnectionSender;
import server.connections.serialization.ObjectMapperProvider;
import server.games.UserInGame;
import spacealert.core.Game;
import spacealert.core.planningPhase.commands.actionCards.FlipCardInHandCommand;
import spacealert.core.planningPhase.eventSequences.premades.Mission1;
import spacealert.core.planningPhase.eventSequences.threatProviders.RandomThreatProvider;
import spacealert.core.planningPhase.eventSequences.threatProviders.ThreatLevel;
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
        var game = new Game(Arrays.asList(new ExternalDecisionMaker(userInGame)), 4, new Mission1(new RandomThreatProvider(Arrays.asList(ThreatLevel.WHITE), Arrays.asList(ThreatLevel.WHITE))));
        game.run();
    }

    public void inputFromConsole(UserInGame userInGame){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!Thread.currentThread().isInterrupted()) {
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
        var serializer = ObjectMapperProvider.provide();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userInGame.receiveInput(serializer.valueToTree(new FlipCardInHandCommand(UUID.randomUUID())).toString());
    }
}
