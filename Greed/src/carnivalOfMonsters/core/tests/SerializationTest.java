package carnivalOfMonsters.core.tests;

import carnivalOfMonsters.core.monsters.Screecher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;

public class SerializationTest {
    @Test
    void test() {
        var sut = new Screecher("Screecher");
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        var json = gson.toJson(sut);
        System.out.println(json);

    }

    @Test
    void test2() throws JsonProcessingException {
        var sut = new Screecher("Screecher");
        var serializer = new ObjectMapper();
        var json = serializer.writeValueAsString(sut);
        System.out.println(json);
    }


}
