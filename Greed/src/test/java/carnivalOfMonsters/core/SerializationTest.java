package carnivalOfMonsters.core;

import carnivalOfMonsters.core.events.Gain2Crowns;
import carnivalOfMonsters.core.lands.BasicNormalLand;
import carnivalOfMonsters.core.monsters.Level2Monster;
import carnivalOfMonsters.core.monsters.Screecher;
import carnivalOfMonsters.core.monsters.TheAncientEnemy;
import carnivalOfMonsters.core.secretGoals.SilverSpoon;
import carnivalOfMonsters.core.staff.Jagermeister;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class SerializationTest {

    @Test
    void screecher() throws JsonProcessingException {
        var sut = new Screecher("Screecher");
        var serializer = new ObjectMapper();
        var json = serializer.writeValueAsString(sut);
        System.out.println(json);
    }

    @Test
    void ancientenemy() throws JsonProcessingException {
        var sut = new TheAncientEnemy("The Ancient Enemy");
        var serializer = new ObjectMapper();
        serializer.configure(MapperFeature.AUTO_DETECT_GETTERS, false);
        var json = serializer.writeValueAsString(sut);
        System.out.println(json);
    }

    @Test
    void event() throws JsonProcessingException {
        var sut = new Gain2Crowns("Gain 2 crowns");
        var serializer = new ObjectMapper();
        serializer.configure(MapperFeature.AUTO_DETECT_GETTERS, false);
        var json = serializer.writeValueAsString(sut);
        System.out.println(json);
    }

    @Test
    void staff() throws JsonProcessingException {
        var sut = new Jagermeister("Jägermeister");
        var serializer = new ObjectMapper();
        serializer.configure(MapperFeature.AUTO_DETECT_GETTERS, false);
        var json = serializer.writeValueAsString(sut);
        System.out.println(json);
    }

    @Test
    void secretGoal() throws JsonProcessingException {
        var sut = new SilverSpoon("Silver Spoon");
        var serializer = new ObjectMapper();
        serializer.configure(MapperFeature.AUTO_DETECT_GETTERS, false);
        var json = serializer.writeValueAsString(sut);
        System.out.println(json);
    }

    @ParameterizedTest
    @EnumSource(LandType.class)
    void level2monster(LandType landType) throws JsonProcessingException {
        var sut = new Level2Monster(landType.toString() + " Level 1", landType);
        var serializer = new ObjectMapper();
        var json = serializer.writeValueAsString(sut);
        System.out.println(json);
    }

    @ParameterizedTest
    @EnumSource(LandType.class)
    void basicLand(LandType landType) throws JsonProcessingException {
        var sut = new BasicNormalLand(landType.toString(), landType);
        var serializer = new ObjectMapper();
        serializer.configure(MapperFeature.AUTO_DETECT_GETTERS, false);
        var json = serializer.writeValueAsString(sut);
        System.out.println(json);
    }

    @Test
    void allCards() throws JsonProcessingException {
        var sut = CardGenerator.createDrawPile();
        var serializer = new ObjectMapper();
        serializer.configure(MapperFeature.AUTO_DETECT_GETTERS, false);
        var json = serializer.writeValueAsString(sut);
        System.out.println(json);
    }

}
