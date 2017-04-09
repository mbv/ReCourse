package by.triumgroup.recourse.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

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

    public static Errors createValidationErrors(Object object, String objectName) {
        return new BeanPropertyBindingResult(object, objectName);
    }
}
