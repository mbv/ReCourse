package by.triumgroup.recourse.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED, reason = "Not allowed")
public class MethodNotAllowedException extends ControllerException {

    public MethodNotAllowedException() {
        super();
    }

    public MethodNotAllowedException(String cause) {
        super(cause);
    }

    public MethodNotAllowedException(Throwable t) {
        super(t);
    }

    public MethodNotAllowedException(String cause, Throwable t) {
        super(cause, t);
    }

}
