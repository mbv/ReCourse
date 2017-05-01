package by.triumgroup.recourse.controller.exception;

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
