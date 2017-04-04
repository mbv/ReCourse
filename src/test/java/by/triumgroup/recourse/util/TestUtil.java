package by.triumgroup.recourse.util;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class TestUtil {
    public static String toJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
