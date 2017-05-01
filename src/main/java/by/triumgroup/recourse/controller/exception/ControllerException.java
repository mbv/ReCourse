package by.triumgroup.recourse.controller.exception;

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
