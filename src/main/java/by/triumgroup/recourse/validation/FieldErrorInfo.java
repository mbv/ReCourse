package by.triumgroup.recourse.validation;

import org.springframework.validation.FieldError;

public class FieldErrorInfo {
    private String field;
    private String message;

    public FieldErrorInfo(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static FieldErrorInfo fromFieldError(FieldError fieldError){
        return new FieldErrorInfo(fieldError.getField(), fieldError.getDefaultMessage());
    }
}