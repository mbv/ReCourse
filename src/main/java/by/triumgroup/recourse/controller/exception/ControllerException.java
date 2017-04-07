package by.triumgroup.recourse.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Error while performing this operation")
public class ControllerException extends RuntimeException {

    public ControllerException() {
        super();
    }

    public ControllerException(String cause) {
        super(cause);
    }

    public ControllerException(Throwable t) {
        super(t);
    }

    public ControllerException(String cause, Throwable t) {
        super(cause, t);
    }

}
