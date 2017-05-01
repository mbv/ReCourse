package by.triumgroup.recourse.controller.exception;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class MethodNotAllowedException extends RequestException {

    public MethodNotAllowedException(HttpMethod method) {
        super(HttpStatus.METHOD_NOT_ALLOWED, new ErrorMessage("Error", "Request method '" + method.name() + "' not supported"));
    }


}
