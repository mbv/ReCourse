package by.triumgroup.recourse.service.exception;

public class ServiceConstraintViolationException extends ServiceException {

    public ServiceConstraintViolationException() {
        super();
    }

    public ServiceConstraintViolationException(String cause) {
        super(cause);
    }

    public ServiceConstraintViolationException(Throwable t) {
        super(t);
    }

    public ServiceConstraintViolationException(String cause, Throwable t) {
        super(cause, t);
    }

}
