package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Set;

public class JSONMapper {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T mapJsonObject(String json, Class<T> clase) {
        try {
            return mapper.readValue(json, clase);
        }catch(Exception e) {
            throw new RuntimeException("Error mapping JSON resopnse",e);
        }
    }

    public static <T> Set<T> mapJsonArray(String jsonArraySTring, Class<T> clase) {
        try{
            return mapper.readValue(
                    jsonArraySTring,
                    mapper.getTypeFactory().constructCollectionType(Set.class, clase)
            );
        }catch(Exception e) {
            throw new RuntimeException("Error mapping JSON resopnse",e);
        }
    }

    public static String mapToJson(Object object) {
        try{
            return mapper.writeValueAsString(object);
        }catch(Exception e) {
            throw new RuntimeException("Error mapping JSON resopnse",e);
        }
    }
}
