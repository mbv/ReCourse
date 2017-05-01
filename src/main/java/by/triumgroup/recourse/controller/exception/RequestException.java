package by.triumgroup.recourse.controller.exception;

import by.triumgroup.recourse.entity.dto.ErrorMessage;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class RequestException extends ControllerException {

    private final HttpStatus status;
    private final List<ErrorMessage> errors;

    public RequestException(HttpStatus status, List<ErrorMessage> errors) {
        this.status = status;
        this.errors = errors;
    }

    public RequestException(HttpStatus status, ErrorMessage... errors) {
        this(status, Arrays.asList(errors));
    }

    public HttpStatus getStatus() {
        return status;
    }

    public List<ErrorMessage> getErrors() {
        return errors;
    }
}
