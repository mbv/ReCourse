package by.triumgroup.recourse.configuration;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Collections;
import java.util.Map;

import static by.triumgroup.recourse.validation.support.Constants.DEFAULT_ERROR_TITLE;

public class CustomErrorAttributes extends DefaultErrorAttributes {

    private static final String EXCEPTION_KEY = "exception";
    private static final String TIMESTAMP_KEY = "timestamp";
    private static final String MESSAGE_KEY = "message";
    private static final String PATH_KEY = "path";
    private static final String ERRORS_KEY = "errors";

    @Override
    public Map<String, Object> getErrorAttributes(
            RequestAttributes requestAttributes,
            boolean includeStackTrace) {

        Map<String, Object> errorAttributes
                = super.getErrorAttributes(requestAttributes, includeStackTrace);
        errorAttributes.remove(EXCEPTION_KEY);
        errorAttributes.remove(TIMESTAMP_KEY);
        errorAttributes.remove(PATH_KEY);
        Object remove = errorAttributes.get(MESSAGE_KEY);
        Object message = Collections.singletonList(new ErrorMessage(
                DEFAULT_ERROR_TITLE,
                remove.toString()
        ));
        errorAttributes.put(ERRORS_KEY, message);
        return errorAttributes;
    }

}
