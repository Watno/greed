package carnivalOfMonsters.core.tests;

import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.lands.BasicNormalLand;
import carnivalOfMonsters.core.monsters.Level2Monster;
import carnivalOfMonsters.core.monsters.Screecher;
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

}
