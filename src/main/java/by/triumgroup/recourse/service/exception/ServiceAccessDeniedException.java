package by.triumgroup.recourse.service.exception;

public class ServiceAccessDeniedException extends ServiceException {

    public ServiceAccessDeniedException() {
        super();
    }

    public ServiceAccessDeniedException(String cause) {
        super(cause);
    }

    public ServiceAccessDeniedException(Throwable t) {
        super(t);
    }

    public ServiceAccessDeniedException(String cause, Throwable t) {
        super(cause, t);
    }

}
