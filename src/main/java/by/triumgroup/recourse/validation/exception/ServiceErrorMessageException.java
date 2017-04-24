package by.triumgroup.recourse.validation.exception;

public class ServiceErrorMessageException extends ValidationException {
    private final String title;
    private final String message;

    public ServiceErrorMessageException(String title, String message){

        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
