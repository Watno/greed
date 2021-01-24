package server.connections.serialization;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class ObjectMapperProvider {
    private static ObjectMapper mapper;

    public static ObjectMapper provide() {
        if (mapper == null) {
            mapper = new ObjectMapper();
            mapper.registerModule(new Jdk8Module());
            mapper.registerModule(new ParameterNamesModule());
            mapper.configure(MapperFeature.AUTO_DETECT_GETTERS, false);
            mapper.configure(MapperFeature.AUTO_DETECT_IS_GETTERS, false);
        }
        return mapper;
    }
}
