package by.triumgroup.recourse.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED, reason = "Disable user instead")
public class UserDeletionNotAllowedException extends ControllerException {

    public UserDeletionNotAllowedException() {
        super();
    }

    public UserDeletionNotAllowedException(String cause) {
        super(cause);
    }

    public UserDeletionNotAllowedException(Throwable t) {
        super(t);
    }

    public UserDeletionNotAllowedException(String cause, Throwable t) {
        super(cause, t);
    }

}
