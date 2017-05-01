package by.triumgroup.recourse.controller.exception;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import static by.triumgroup.recourse.validation.support.Constants.DEFAULT_ERROR_TITLE;

public class MethodNotAllowedException extends RequestException {

    public MethodNotAllowedException(HttpMethod method) {
        super(HttpStatus.METHOD_NOT_ALLOWED, new ErrorMessage(DEFAULT_ERROR_TITLE, "Request method '" + method.name() + "' not supported"));
    }


}
