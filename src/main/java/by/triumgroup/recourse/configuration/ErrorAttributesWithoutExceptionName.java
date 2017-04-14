package by.triumgroup.recourse.configuration;

import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Map;

public class ErrorAttributesWithoutExceptionName extends DefaultErrorAttributes {

    private static final String EXCEPTION_KEY = "exception";

    @Override
    public Map<String, Object> getErrorAttributes(
            RequestAttributes requestAttributes,
            boolean includeStackTrace) {

        Map<String, Object> errorAttributes
                = super.getErrorAttributes(requestAttributes, includeStackTrace);
        errorAttributes.remove(EXCEPTION_KEY);
        return errorAttributes;
    }

}
