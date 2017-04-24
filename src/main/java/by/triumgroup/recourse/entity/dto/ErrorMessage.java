package by.triumgroup.recourse.entity.dto;

import org.springframework.validation.FieldError;

public class ErrorMessage {
    private String title;
    private String message;

    public ErrorMessage(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ErrorMessage fromFieldError(FieldError fieldError){
        return new ErrorMessage(fieldError.getField(), fieldError.getDefaultMessage());
    }
}