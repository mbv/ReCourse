package by.triumgroup.recourse.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Access denied")
public class AccessDeniedException extends ControllerException {

    public AccessDeniedException() {
        super();
    }

    public AccessDeniedException(String cause) {
        super(cause);
    }

    public AccessDeniedException(Throwable t) {
        super(t);
    }

    public AccessDeniedException(String cause, Throwable t) {
        super(cause, t);
    }

}
